import {Component, OnInit} from '@angular/core';
import {BookService} from "../book/service/book.service";
import {PublisherService} from "../publisher/service/publisher.service";
import {LibraryService} from "../library/service/library.service";
import {AuthorService} from "../author/service/author.service";
import {catchError, forkJoin, of} from "rxjs";


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
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

    cards: HomePanel[] = [];

    constructor(
        private bookService: BookService,
        private authorService: AuthorService,
        private publisherService: PublisherService,
        private libraryService: LibraryService
    ) {}

    ngOnInit(): void {
        forkJoin({
            bookCount: this.bookService.getBooksCount().pipe(catchError(() => of(-1))),
            authorCount: this.authorService.getAuthorsCount().pipe(catchError(() => of(-1))),
            publisherCount: this.publisherService.getPublishersCount().pipe(catchError(() => of(-1))),
            libraryCount: this.libraryService.getLibrariesCount().pipe(catchError(() => of(-1)))
        }).subscribe(({ bookCount, authorCount, publisherCount, libraryCount }) => {
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
