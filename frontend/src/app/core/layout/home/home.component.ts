import {Component, OnInit} from '@angular/core';
import {catchError, forkJoin, of} from "rxjs";
import {RouterLink} from "@angular/router";
import {AuthorService} from "@features/author/services/author.service";
import {PublisherService} from "@features/publisher/service/publisher.service";
import {LibraryService} from "@features/library/services/library.service";
import {BookService} from "@features/book/services/book.service";
import {UserService} from "@core/auth/services/user.service";
import * as Pages from "../../../pages";

interface HomePanel {
  iconKey: 'books' | 'authors' | 'publishers' | 'libraries';
  label: string;
  count: number | string;
  route: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [
    RouterLink
  ]
})
export class HomeComponent implements OnInit {
  protected readonly PAGES = Pages;
  protected cards: HomePanel[] = [];

  constructor(
    protected loginService: UserService,
    private bookService: BookService,
    private authorService: AuthorService,
    private publisherService: PublisherService,
    private libraryService: LibraryService
  ) {
  }

  public ngOnInit(): void {
    if (!this.loginService.isLoggedIn()) {
      return;
    }

    forkJoin({
      bookCount: this.bookService.getBooksCount().pipe(catchError(() => of(-1))),
      authorCount: this.authorService.getAuthorsCount().pipe(catchError(() => of(-1))),
      publisherCount: this.publisherService.getPublishersCount().pipe(catchError(() => of(-1))),
      libraryCount: this.libraryService.getLibrariesCount().pipe(catchError(() => of(-1)))
    }).subscribe(({bookCount, authorCount, publisherCount, libraryCount}) => {
      const safeCount = (count: number) => count >= 0 ? count : '?';

      this.cards = [
        {
          iconKey: 'books',
          label: 'Books',
          count: safeCount(bookCount),
          route: '/books'
        },
        {
          iconKey: 'authors',
          label: 'Authors',
          count: safeCount(authorCount),
          route: '/authors'
        },
        {
          iconKey: 'publishers',
          label: 'Publishers',
          count: safeCount(publisherCount),
          route: '/publishers'
        },
        {
          iconKey: 'libraries',
          label: 'Libraries',
          count: safeCount(libraryCount),
          route: '/libraries'
        }
      ];
    });
  }
}
