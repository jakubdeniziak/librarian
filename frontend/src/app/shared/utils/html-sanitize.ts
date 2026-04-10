import DOMPurify from 'dompurify';

let hooksInstalled = false;

function ensureHooksInstalled(): void {
  if (hooksInstalled) return;
  hooksInstalled = true;
  DOMPurify.addHook('afterSanitizeAttributes', (node) => {
    if (!(node instanceof HTMLAnchorElement)) return;
    const target = (node.getAttribute('target') || '').toLowerCase();
    if (target === '_blank') {
      const rel = (node.getAttribute('rel') || '').toLowerCase();
      const tokens = new Set(rel.split(/\s+/).filter(Boolean));
      tokens.add('noopener');
      tokens.add('noreferrer');
      node.setAttribute('rel', Array.from(tokens).join(' '));
    }
  });
}

export function sanitizeHtml(html: string | undefined | null): string {
  if (!html) return '';
  ensureHooksInstalled();
  return DOMPurify.sanitize(html, {
    USE_PROFILES: {html: true},
    ALLOWED_TAGS: [
      'p', 'br',
      'strong', 'b',
      'em', 'i',
      'u',
      'blockquote',
      'ul', 'ol', 'li',
      'a',
      'span'
    ],
    ALLOWED_ATTR: ['href', 'title', 'target', 'rel'],
    ALLOW_DATA_ATTR: false,
    ALLOWED_URI_REGEXP: /^(?:(?:https?|mailto):|[^a-z]|[a-z+.-]+(?:[^a-z+.-:]|$))/i,
    FORBID_ATTR: ['style', 'srcset'],
    ADD_ATTR: ['rel'],
    SANITIZE_DOM: true,
  });
}
