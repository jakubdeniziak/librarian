import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {catchError, of} from 'rxjs';

import * as Pages from '../../../../pages';
import {DashboardResponse} from '../../models/dashboard.model';
import {DashboardService} from '../../services/dashboard.service';
import {UserService} from '@core/auth/services/user.service';
import {SearchService} from '@core/search/services/search.service';
import {SearchHit, SearchRequest, SearchResponse} from '@core/search/models/search.model';
import {PageHeaderComponent} from '@shared/components/page-header/page-header.component';

interface DashboardPanel {
  iconKey: 'books' | 'authors' | 'publishers' | 'libraries';
  label: string;
  count: number | string;
  route: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, FormsModule, PageHeaderComponent],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {
  protected readonly PAGES = Pages;

  protected cards: DashboardPanel[] = [];

  protected searchQuery = '';
  protected isAdvancedSearchOpen = false;
  protected isSearching = false;
  protected searchError = false;
  protected searchResults: SearchResponse | null = null;

  protected includeBooks = true;
  protected includeAuthors = true;
  protected includePublishers = true;
  protected includeLibraries = false;

  protected yearFrom?: number;
  protected yearTo?: number;
  protected format?: SearchRequest['format'];

  constructor(
    protected userService: UserService,
    private dashboardService: DashboardService,
    private searchService: SearchService
  ) {
  }

  public ngOnInit(): void {
    if (!this.userService.isLoggedIn()) {
      return;
    }

    const safe = (n: number | null | undefined): number | string => (typeof n === 'number' && n >= 0) ? n : '?';

    const unknownDashboard: DashboardResponse = {
      counts: {
        libraries: -1,
        books: -1,
        authors: -1,
        publishers: -1
      }
    };

    this.dashboardService.getMyDashboard().pipe(
      catchError(() => of(unknownDashboard))
    ).subscribe((dashboard) => {
      const c = dashboard.counts;

      this.cards = [
        {iconKey: 'books', label: 'Books', count: safe(c.books), route: Pages.BOOKS},
        {iconKey: 'authors', label: 'Authors', count: safe(c.authors), route: Pages.AUTHORS},
        {iconKey: 'publishers', label: 'Publishers', count: safe(c.publishers), route: Pages.PUBLISHERS},
        {iconKey: 'libraries', label: 'Libraries', count: safe(c.libraries), route: Pages.LIBRARIES},
      ];
    });
  }

  protected toggleAdvancedSearch(): void {
    this.isAdvancedSearchOpen = !this.isAdvancedSearchOpen;
  }

  protected clearSearch(): void {
    this.searchQuery = '';
    this.searchResults = null;
    this.searchError = false;
    this.isSearching = false;
  }

  protected onSearch(): void {
    const q = (this.searchQuery ?? '').trim();
    if (!q) {
      this.searchResults = null;
      this.searchError = false;
      return;
    }

    const request: SearchRequest = {
      query: q,
      includeBooks: this.includeBooks,
      includeAuthors: this.includeAuthors,
      includePublishers: this.includePublishers,
      includeLibraries: this.includeLibraries,
      yearFrom: this.yearFrom,
      yearTo: this.yearTo,
      format: this.format,
      page: 0,
      size: 8,
    };

    this.isSearching = true;
    this.searchError = false;

    this.searchService.search(request).pipe(
      catchError(() => {
        this.searchError = true;
        return of({hits: [], total: 0} satisfies SearchResponse);
      })
    ).subscribe((res: SearchResponse | null) => {
      this.isSearching = false;
      this.searchResults = res;
    });
  }

  protected hitRouterLink(hit: SearchHit): string {
    switch (hit.type) {
      case 'book':
        return `${Pages.BOOKS}/${hit.id}`;
      case 'author':
        return `${Pages.AUTHORS}/${hit.id}`;
      case 'publisher':
        return `${Pages.PUBLISHERS}/${hit.id}`;
      case 'library':
        return `${Pages.LIBRARIES}/${hit.id}`;
      default:
        return Pages.HOME;
    }
  }
}
