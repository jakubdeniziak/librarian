import {
  AfterViewInit,
  Component,
  ComponentRef,
  OnDestroy,
  OnInit,
  Type,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "@core/auth/services/user.service";
import {AUTHORS} from "../../../../pages";
import {AuthorService} from "@features/author/services/author.service";
import {FormsModule} from "@angular/forms";
import {DEFAULT_AUTHORS_PAGE_SIZE} from "@features/author/models/authors-query.model";
import {PagedResult} from "@shared/models/paged-result.model";
import {Author} from "@features/author/models/author.model";
import {AuthorInsights} from "@features/author/models/author-insights.model";
import {Subject, Subscription} from "rxjs";
import {debounceTime, distinctUntilChanged} from "rxjs/operators";

@Component({
  selector: 'app-author-list',
  standalone: true,
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.css',
  imports: [
    RouterLink,
    PageHeaderComponent,
    FormsModule,
  ]
})
export class AuthorListComponent implements OnInit, AfterViewInit, OnDestroy {
  protected readonly AUTHORS = AUTHORS;

  protected scope: 'my' | 'discover' = 'my';

  protected searchQuery = '';

  protected pageSize = DEFAULT_AUTHORS_PAGE_SIZE;
  protected page = 0; // 0-based

  protected result: PagedResult<Author> | undefined;

  protected insights: AuthorInsights | undefined;
  protected insightsError: string | undefined;
  private insightsLoaded = false;

  protected isLoading = false;
  protected loadError: string | undefined;

  protected deleting = new Set<string>();

  protected isCsvImportOpen = false;
  protected csvImportComponent?: Type<unknown>;
  protected isCsvImportLoading = false;

  private csvImportHost?: ViewContainerRef;

  @ViewChild('csvImportHost', {read: ViewContainerRef})
  private set csvImportHostRef(vcr: ViewContainerRef | undefined) {
    this.csvImportHost = vcr;
    // If the user reopened the modal and the component is already loaded,
    // the host becomes available only after view init; mount then.
    this.mountCsvImportComponent();
  }

  private csvImportRef?: ComponentRef<any>;

  private sub?: Subscription;
  private searchInput$ = new Subject<string>();
  private searchSub?: Subscription;

  constructor(
    protected userService: UserService,
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  public ngOnInit() {
    this.searchSub = this.searchInput$.pipe(
      // Avoid spamming backend on each keypress.
      debounceTime(350),
      distinctUntilChanged(),
    ).subscribe(q => {
      void this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          scope: this.scope,
          page: 0,
          q: q?.trim() || null,
        },
        queryParamsHandling: 'merge',
        replaceUrl: true,
      });
    });

    this.sub = this.route.queryParamMap.subscribe(params => {
      const scope = (params.get('scope') ?? 'my') as 'my' | 'discover';
      this.scope = (scope === 'discover') ? 'discover' : 'my';

      const page = Number(params.get('page') ?? '0');
      this.page = Number.isFinite(page) && page >= 0 ? page : 0;

      this.searchQuery = params.get('q') ?? '';

      if (!this.insightsLoaded) {
        this.insightsLoaded = true;
        this.loadInsights();
      }
      this.loadAuthors();
    });
  }

  public ngAfterViewInit(): void {
    // In case csvImportHost was available only after view init.
    this.mountCsvImportComponent();
  }

  public ngOnDestroy(): void {
    this.sub?.unsubscribe();
    this.searchSub?.unsubscribe();
    this.destroyCsvImportComponent();
  }

  protected onDelete(authorId: string) {
    if (this.deleting.has(authorId)) return;

    const confirmed = window.confirm('Delete this author? This action cannot be undone.');
    if (!confirmed) return;

    this.deleting.add(authorId);
    this.authorService.deleteAuthor(authorId).subscribe({
      next: () => this.loadAuthors(),
      error: () => {
        this.loadError = 'Delete failed. Please try again.';
        this.deleting.delete(authorId);
      }
    });
  }

  protected clearSearch(): void {
    this.searchQuery = '';
    this.searchInput$.next('');
  }

  protected openCsvImport(): void {
    if (this.isCsvImportOpen) return;
    this.isCsvImportOpen = true;

    if (this.csvImportComponent) {
      // Component code already loaded; just mount a fresh instance (once host exists).
      this.mountCsvImportComponent();
      return;
    }
    this.isCsvImportLoading = true;

    // Lazy-load to avoid main-thread jank from loading papaparse + uuid etc.
    import('@features/author/components/author-csv-import/author-csv-import.component')
      .then(m => {
        this.csvImportComponent = m.AuthorCsvImportComponent;
        this.mountCsvImportComponent();
      })
      .catch(() => {
        // keep modal open but user will see loading fallback in template
      })
      .finally(() => {
        this.isCsvImportLoading = false;
      });
  }

  protected closeCsvImport(): void {
    this.isCsvImportOpen = false;
    this.destroyCsvImportComponent();
  }

  protected onCsvImportLoadingOverlayClick(event: MouseEvent): void {
    if ((event.target as HTMLElement)?.id === 'author-csv-import-loading-overlay') {
      this.closeCsvImport();
    }
  }

  private mountCsvImportComponent(): void {
    if (!this.isCsvImportOpen) return;
    if (!this.csvImportComponent) return;
    if (!this.csvImportHost) return;
    if (this.csvImportRef) return;

    this.csvImportHost.clear();
    this.csvImportRef = this.csvImportHost.createComponent(this.csvImportComponent as Type<any>);

    // inputs
    this.csvImportRef.setInput('open', true);

    // outputs
    const inst = this.csvImportRef.instance;
    if (inst?.closed?.subscribe) {
      inst.closed.subscribe(() => this.closeCsvImport());
    }
    if (inst?.completed?.subscribe) {
      inst.completed.subscribe(() => this.loadAuthors());
    }
  }

  private destroyCsvImportComponent(): void {
    try {
      this.csvImportRef?.destroy();
    } finally {
      this.csvImportRef = undefined;
      this.csvImportHost?.clear();
    }
  }

  protected onSearchInput(): void {
    this.searchInput$.next(this.searchQuery ?? '');
  }

  protected setScope(scope: 'my' | 'discover'): void {
    if (this.scope === scope) return;
    void this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {scope, page: 0},
      queryParamsHandling: 'merge',
      // keep SPA feel: don't clutter history; avoid scroll jumps
      replaceUrl: true,
    });
  }

  protected goToPage(page: number): void {
    if (page < 0) return;
    void this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {page},
      queryParamsHandling: 'merge',
      replaceUrl: true,
    });
  }

  protected loadAuthors(): void {
    this.isLoading = true;
    this.loadError = undefined;
    this.result = undefined;

    const q = this.searchQuery?.trim();
    const request = {
      page: this.page,
      size: this.pageSize,
      q: q?.length ? q : undefined,
      // Default suggestion: stable sort by name. Backend may choose to ignore this
      // for Discover and return a "top" (semi-random) slice instead.
      sort: 'lastName,asc',
    };

    const obs = (this.scope === 'my')
      ? this.authorService.getMyAuthorsPaged(request)
      : this.authorService.getDiscoverAuthorsPaged(request);

    obs.subscribe({
      next: res => {
        this.result = res;
        this.isLoading = false;
        this.deleting.clear();
      },
      error: () => {
        this.isLoading = false;
        this.loadError = 'Could not load authors. Please refresh and try again.';
      }
    });
  }

  protected loadInsights(): void {
    // Insights are optional; if endpoint doesn't exist yet we just hide the section.
    this.insightsError = undefined;
    this.insights = undefined;

    this.authorService.getMyAuthorInsights().subscribe({
      next: r => {
        this.insights = r;
      },
      error: () => {
        this.insightsError = 'Insights are not available yet.';
      }
    });
  }

  protected get canPrev(): boolean {
    return (this.result?.page ?? 0) > 0;
  }

  protected get canNext(): boolean {
    if (!this.result) return false;
    return this.result.page < this.result.totalPages - 1;
  }

  protected get hasValidPaging(): boolean {
    if (!this.result) return false;
    const r = this.result;
    return (
      Number.isFinite(r.page) && r.page >= 0 &&
      Number.isFinite(r.totalPages) && r.totalPages >= 0 &&
      Number.isFinite(r.totalItems) && r.totalItems >= 0
    );
  }
}
