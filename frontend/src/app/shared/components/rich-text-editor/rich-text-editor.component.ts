import {Component, ElementRef, EventEmitter, forwardRef, HostListener, Input, Output, ViewChild} from '@angular/core';
import {ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
  selector: 'app-rich-text-editor',
  standalone: true,
  templateUrl: './rich-text-editor.component.html',
  styleUrl: './rich-text-editor.component.css',
  imports: [FormsModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => RichTextEditorComponent),
      multi: true,
    },
  ],
})
export class RichTextEditorComponent implements ControlValueAccessor {
  @Input() label = 'Description';
  @Input() placeholder = 'Write something...';
  @Input() helpText?: string;
  @Input() disabled = false;
  @Input() minHeightPx = 160;
  @Input() trimOutput = true;
  @Input() invalid = false;
  @Output() htmlChange = new EventEmitter<string>();
  @ViewChild('editor', {static: true}) editor!: ElementRef<HTMLDivElement>;

  protected html = '';
  protected touched = false;
  protected formatState = {
    bold: false,
    italic: false,
    link: false,
  };
  protected linkPopupOpen = false;
  protected linkUrl = '';

  private savedSelection: Range | null = null;
  private onChange: (value: string) => void = () => {
  };
  private onTouched: () => void = () => {
  };

  writeValue(value: string | null): void {
    this.html = value ?? '';
    this.renderHtml();
  }

  registerOnChange(fn: (value: string) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
    if (this.editor?.nativeElement) {
      this.editor.nativeElement.setAttribute('contenteditable', String(!isDisabled));
    }
  }

  protected markTouched(): void {
    if (!this.touched) {
      this.touched = true;
      this.onTouched();
    }
  }

  protected onInput(): void {
    this.markTouched();
    this.enforceLinkPolicyInEditor();
    const raw = this.editor.nativeElement.innerHTML ?? '';
    const next = this.trimOutput ? raw.trim() : raw;
    this.html = next;
    this.onChange(next);
    this.htmlChange.emit(next);
    this.updateFormatState();
  }

  protected onKeydown(): void {
  }

  @HostListener('document:keydown.escape')
  protected onEscape(): void {
    if (this.linkPopupOpen) {
      this.closeLinkPopup();
    }
  }

  protected format(cmd: 'bold' | 'italic'): void {
    if (this.disabled) return;
    this.focusSync();
    if (cmd === 'bold') {
      this.toggleInlineTag('strong');
    } else {
      this.toggleInlineTag('em');
    }
    this.onInput();
  }

  protected onSelectionChanged(): void {
    this.updateFormatState();
  }

  protected openLinkPopup(): void {
    if (this.disabled) return;
    this.focusSync();
    this.saveSelection();
    this.linkUrl = this.getSelectedLinkHref() ?? '';
    this.linkPopupOpen = true;
  }

  protected closeLinkPopup(): void {
    this.linkPopupOpen = false;
    this.linkUrl = '';
    this.focus();
  }

  protected confirmLink(): void {
    if (this.disabled) return;
    const normalized = this.normalizeUrl(this.linkUrl);
    if (!normalized) return;
    this.focusSync();
    this.restoreSelection();
    const anchor = this.getSelectedAnchorElement();
    if (anchor) {
      anchor.setAttribute('href', normalized);
      this.applyLinkTargetPolicy(anchor);
    } else {
      this.wrapSelectionWithLink(normalized);
    }
    this.linkPopupOpen = false;
    this.linkUrl = '';
    this.onInput();
  }

  private applyLinkTargetPolicy(anchor: HTMLAnchorElement): void {
    anchor.setAttribute('target', '_blank');
    anchor.setAttribute('rel', 'noopener noreferrer');
  }

  protected removeLink(): void {
    if (this.disabled) return;
    this.focusSync();
    this.unwrapLinkAtSelection();
    this.onInput();
  }

  protected clearFormatting(): void {
    if (this.disabled) return;
    this.focusSync();
    this.removeInlineFormattingInSelection();
    this.unwrapLinkAtSelection();
    this.onInput();
  }

  private wrapSelectionWithLink(href: string): void {
    const sel = window.getSelection();
    if (!sel || sel.rangeCount === 0) return;
    const range = sel.getRangeAt(0);
    if (!this.editor.nativeElement.contains(range.commonAncestorContainer)) return;
    if (range.collapsed) return;
    const a = document.createElement('a');
    a.setAttribute('href', href);
    this.applyLinkTargetPolicy(a);
    try {
      a.appendChild(range.extractContents());
      range.insertNode(a);
      sel.removeAllRanges();
      const next = document.createRange();
      next.selectNodeContents(a);
      sel.addRange(next);
    } catch {
    }
  }

  private unwrapLinkAtSelection(): void {
    const a = this.getSelectedAnchorElement();
    if (!a) return;
    const parent = a.parentNode;
    if (!parent) return;
    while (a.firstChild) {
      parent.insertBefore(a.firstChild, a);
    }
    parent.removeChild(a);
  }

  private removeInlineFormattingInSelection(): void {
    const sel = window.getSelection();
    if (!sel || sel.rangeCount === 0) return;
    const range = sel.getRangeAt(0);
    if (!this.editor.nativeElement.contains(range.commonAncestorContainer)) return;
    if (range.collapsed) return;

    const frag = range.extractContents();
    this.stripFormattingElements(frag);
    range.insertNode(frag);

    sel.removeAllRanges();
    const next = document.createRange();
    next.setStart(range.startContainer, range.startOffset);
    next.collapse(true);
    sel.addRange(next);
  }

  private stripFormattingElements(root: Node): void {
    const walker = document.createTreeWalker(root, NodeFilter.SHOW_ELEMENT);
    const toUnwrap: Element[] = [];
    while (walker.nextNode()) {
      const el = walker.currentNode as Element;
      if (['STRONG', 'B', 'EM', 'I', 'U', 'SPAN'].includes(el.tagName)) {
        toUnwrap.push(el);
      }
    }
    for (const el of toUnwrap.reverse()) {
      const parent = el.parentNode;
      if (!parent) continue;
      while (el.firstChild) parent.insertBefore(el.firstChild, el);
      parent.removeChild(el);
    }
  }

  private focus(): void {
    queueMicrotask(() => this.editor.nativeElement.focus());
  }

  private focusSync(): void {
    this.editor.nativeElement.focus();
  }

  private updateFormatState(): void {
    const selInfo = this.getSelectionInfo();
    this.formatState.bold = selInfo.bold;
    this.formatState.italic = selInfo.italic;
    this.formatState.link = selInfo.link;
  }

  private saveSelection(): void {
    const sel = window.getSelection();
    if (!sel || sel.rangeCount === 0) {
      this.savedSelection = null;
      return;
    }
    const range = sel.getRangeAt(0);
    if (!this.editor.nativeElement.contains(range.commonAncestorContainer)) {
      this.savedSelection = null;
      return;
    }
    this.savedSelection = range.cloneRange();
  }

  private restoreSelection(): void {
    if (!this.savedSelection) return;
    const sel = window.getSelection();
    if (!sel) return;
    sel.removeAllRanges();
    sel.addRange(this.savedSelection);
  }

  private toggleInlineTag(tagName: 'strong' | 'em'): void {
    const sel = window.getSelection();
    if (!sel || sel.rangeCount === 0) return;
    const range = sel.getRangeAt(0);
    if (!this.editor.nativeElement.contains(range.commonAncestorContainer)) return;
    if (range.collapsed) return;
    const wrapper = document.createElement(tagName);
    try {
      wrapper.appendChild(range.extractContents());
      range.insertNode(wrapper);
      sel.removeAllRanges();
      const next = document.createRange();
      next.selectNodeContents(wrapper);
      sel.addRange(next);
    } catch {
    }
  }

  private getSelectionInfo(): { bold: boolean; italic: boolean; link: boolean } {
    const a = this.getSelectedAnchorElement();
    const el = this.getSelectionContainerElement();
    const bold = this.hasAncestorTag(el, ['STRONG', 'B']);
    const italic = this.hasAncestorTag(el, ['EM', 'I']);
    return {bold, italic, link: !!a};
  }

  private getSelectionContainerElement(): Element | null {
    const node = this.getSelectionNode();
    return node instanceof Element ? node : null;
  }

  private hasAncestorTag(start: Element | null, tagNames: string[]): boolean {
    if (!start) return false;
    let node: Node | null = start;
    while (node && node !== this.editor.nativeElement) {
      if (node instanceof Element && tagNames.includes(node.tagName)) return true;
      node = node.parentNode;
    }
    return false;
  }

  private getSelectedAnchorElement(): HTMLAnchorElement | null {
    let node: Node | null = this.getSelectionNode();
    if (!node) return null;

    while (node && node !== this.editor.nativeElement) {
      if (node instanceof HTMLAnchorElement) return node;
      node = node.parentNode;
    }
    return null;
  }

  private getSelectionNode(): Node | null {
    const sel = window.getSelection();
    if (!sel || sel.rangeCount === 0) return null;
    const range = sel.getRangeAt(0);
    if (!this.editor.nativeElement.contains(range.commonAncestorContainer)) return null;
    let node: Node | null = range.commonAncestorContainer;
    if (node.nodeType === Node.TEXT_NODE) node = node.parentNode;
    return node;
  }

  private getSelectedLinkHref(): string | null {
    const a = this.getSelectedAnchorElement();
    return a?.getAttribute('href') ?? null;
  }

  private enforceLinkPolicyInEditor(): void {
    const root = this.editor.nativeElement;
    const anchors = root.querySelectorAll('a[href]');
    for (const node of Array.from(anchors)) {
      if (!(node instanceof HTMLAnchorElement)) continue;
      this.applyLinkTargetPolicy(node);
    }
  }

  private normalizeUrl(url: string): string | null {
    const trimmed = (url ?? '').trim();
    if (!trimmed) return null;
    if (/^(https?:\/\/|mailto:)/i.test(trimmed)) return trimmed;
    return `https://${trimmed}`;
  }

  private renderHtml(): void {
    if (!this.editor?.nativeElement) return;
    this.editor.nativeElement.innerHTML = this.html || '';
    this.editor.nativeElement.setAttribute('contenteditable', String(!this.disabled));
    this.enforceLinkPolicyInEditor();
    this.updateFormatState();
  }
}
