import {Component, OnInit} from '@angular/core';
import {catchError, forkJoin, of} from "rxjs";
import {RouterLink} from "@angular/router";
import {AuthorService} from "@features/author/services/author.service";
import {PublisherService} from "@features/publisher/service/publisher.service";
import {UserService} from "../../../user/service/user.service";
import {NgOptimizedImage} from "@angular/common";
import {LibraryService} from "@features/library/services/library.service";
import {BookService} from "@features/book/services/book.service";

interface HomePanel {
  image: string;
  alt: string;
  label: string;
  count: number | string;
  route: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [
    RouterLink,
    NgOptimizedImage
  ]
})
export class HomeComponent implements OnInit {
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
          image: 'assets/images/books.png',
          alt: 'Books',
          label: 'Books',
          count: safeCount(bookCount),
          route: '/books'
        },
        {
          image: 'assets/images/authors.png',
          alt: 'Authors',
          label: 'Authors',
          count: safeCount(authorCount),
          route: '/authors'
        },
        {
          image: 'assets/images/publishers.png',
          alt: 'Publishers',
          label: 'Publishers',
          count: safeCount(publisherCount),
          route: '/publishers'
        },
        {
          image: 'assets/images/libraries.png',
          alt: 'Libraries',
          label: 'Libraries',
          count: safeCount(libraryCount),
          route: '/libraries'
        }
      ];
    });
  }
}
