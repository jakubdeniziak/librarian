import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Books} from "@features/book/models/books.model";
import {BookService} from "@features/book/services/book.service";
import {AuthorDetails} from "@features/author/models/author-details.model";
import {UserService} from "@core/auth/services/user.service";
import {AuthorService} from "@features/author/services/author.service";
import {AUTHORS, BOOKS} from "@app/pages";
import {DatePipe} from "@angular/common";
import {SanitizeHtmlPipe} from "@shared/pipes/sanitize-html.pipe";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css',
  imports: [
    RouterLink,
    DatePipe,
    SanitizeHtmlPipe,
  ]
})
export class AuthorDetailsComponent implements OnInit, OnDestroy {
  protected readonly AUTHORS = AUTHORS;
  protected readonly BOOKS = BOOKS;

  protected author: AuthorDetails | undefined;
  protected books: Books | undefined;

  protected fabBottomPx: number | null = null;

  private rafId: number | null = null;
  private resizeObserver: ResizeObserver | null = null;

  constructor(protected userService: UserService,
              private authorService: AuthorService,
              private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.authorService.getAuthor(params['uuid']).subscribe(author => this.author = author);
      this.bookService.getBooksByAuthor(params['uuid']).subscribe(books => this.books = books);
    });

    this.scheduleFabRecalc();
    window.addEventListener('scroll', this.onWindowChanged, {passive: true});
    window.addEventListener('resize', this.onWindowChanged, {passive: true});

    const footer = document.getElementById('app-footer');
    if (footer && typeof ResizeObserver !== 'undefined') {
      this.resizeObserver = new ResizeObserver(() => this.scheduleFabRecalc());
      this.resizeObserver.observe(footer);
    }
  }

  public ngOnDestroy(): void {
    window.removeEventListener('scroll', this.onWindowChanged);
    window.removeEventListener('resize', this.onWindowChanged);
    if (this.resizeObserver) this.resizeObserver.disconnect();
    if (this.rafId !== null) cancelAnimationFrame(this.rafId);
  }

  private onWindowChanged = (): void => {
    this.scheduleFabRecalc();
  };

  private scheduleFabRecalc(): void {
    if (this.rafId !== null) return;
    this.rafId = requestAnimationFrame(() => {
      this.rafId = null;
      this.recalcFabBottom();
    });
  }

  private recalcFabBottom(): void {
    const footer = document.getElementById('app-footer');
    const minBottom = 24;
    const gap = 16;

    if (!footer) {
      this.fabBottomPx = minBottom;
      return;
    }

    const rect = footer.getBoundingClientRect();
    const overlap = window.innerHeight - rect.top;
    const desired = Math.max(minBottom, overlap + gap);
    this.fabBottomPx = Math.round(desired);
  }

  protected onDelete(authorId: string): void {
    this.authorService.deleteAuthor(authorId).subscribe(() => {
      this.router.navigate([AUTHORS]).then(success => {
        if (!success) console.error('Navigation to /authors failed');
      });
    });
  }

  protected onImageError(event: Event): void {
    const img = event.target as HTMLImageElement | null;
    if (!img) return;
    if (img.src.includes('/assets/images/author-placeholder.svg')) return;
    img.src = '/assets/images/author-placeholder.svg';
  }
}
