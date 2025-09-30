import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {LIBRARIES} from "../../../../pages";
import {LibraryBookForm} from "@features/library-book/models/library-book.form";
import {BookService} from "../../../../book/service/book.service";
import {LibraryBookService} from "@features/library-book/services/library-book.service";
import {LibraryBooks} from "@features/library-book/models/library.books";
import {LibraryBook} from "@features/library-book/models/library.book";
import {Observable} from "rxjs";
import {BookDetails} from "../../../../book/model/book-details";

@Component({
  selector: 'app-library-add-book',
  templateUrl: './library-add-book.component.html',
  styleUrl: './library-add-book.component.css',
  imports: [
    PageHeaderComponent,
    FormsModule,
    RouterLink
  ]
})
export class LibraryAddBookComponent implements OnInit {
  protected readonly LIBRARIES = LIBRARIES;

  protected libraryId: string | undefined;
  protected bookId: string | undefined;
  protected books: LibraryBooks | undefined;
  protected libraryBook: LibraryBookForm | undefined;

  constructor(private libraryBookService: LibraryBookService,
              private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.bookService.getBooksByLibrary(params['uuid'])
        .subscribe(books => this.books = books);
    });
    this.libraryBook = {numberOfCopies: 0};
  }

  protected onSubmit() {
    this.libraryBookService.addBookToLibrary(this.libraryId!, this.bookId!, this.libraryBook!)
      .subscribe(() => this.router.navigate([LIBRARIES, this.libraryId!]));
  }

  protected getBookTitle(bookId: string): Observable<BookDetails> {
    return this.bookService.getBook(bookId);
  }

  protected trackById(libraryBook: LibraryBook) {
    return `${libraryBook.bookId}-${libraryBook.libraryId}`;
  }
}
