import {Component, Input, OnInit} from '@angular/core';
import {BOOKS} from "../../../../pages";
import {LibraryService} from "@features/library/services/library.service";
import {BookService} from "../../../../book/service/book.service";
import {LibraryBook} from "@features/library-book/models/library.book";
import {RouterLink} from "@angular/router";
import {LibraryBooks} from "@features/library-book/models/library.books";

@Component({
  selector: 'app-library-book-list',
  templateUrl: './library-book-list.component.html',
  imports: [
    RouterLink
  ],
  styleUrl: './library-book-list.component.css'
})
export class LibraryBookList implements OnInit {
  protected readonly BOOKS = BOOKS;

  @Input() public libraryId!: string;
  protected libraryBooks: LibraryBooks | undefined
  protected bookTitles: { [bookId: string]: string } = {};

  constructor(private libraryService: LibraryService, private bookService: BookService) {
  }

  public ngOnInit(): void {
    if (this.libraryId) {
      this.bookService.getBooksByLibrary(this.libraryId)
        .subscribe(books => {
          this.libraryBooks = books
          this.fetchBookTitles();
        });
    }
  }

  protected trackById(libraryBook: LibraryBook) {
    return `${libraryBook.bookId}-${libraryBook.libraryId}`;
  }

  private fetchBookTitles(): void {
    this.libraryBooks?.libraryBooks.forEach(libraryBook => {
      this.bookService.getBook(libraryBook.bookId).subscribe(book => {
        this.bookTitles[libraryBook.bookId] = book.title;
      });
    });
  }
}

