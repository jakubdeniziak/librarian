import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {sanitizeHtml} from '@shared/utils/html-sanitize';

@Pipe({
  name: 'sanitizeHtml',
  standalone: true,
})
export class SanitizeHtmlPipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) {
  }

  transform(value: string | undefined | null): SafeHtml {
    const sanitized = sanitizeHtml(value);
    return this.sanitizer.bypassSecurityTrustHtml(sanitized);
  }
}
