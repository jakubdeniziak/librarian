import {Component, OnInit} from '@angular/core';
import {LibraryService} from "../../service/library.service";
import {ActivatedRoute} from "@angular/router";
import {LibraryDetails} from "../../model/library-details";
import {BookService} from "../../../book/service/book.service";
import {LibraryBooks} from "../../model/library-book/library-books";

@Component({
  selector: 'app-library-details',
  templateUrl: './library-details.component.html',
  styleUrl: './library-details.component.css'
})
export class LibraryDetailsComponent implements OnInit {
    library: LibraryDetails | undefined
    libraryBooks: LibraryBooks | undefined
    bookTitles: { [bookId: string]: string } = {};

    constructor(private service: LibraryService, private bookService: BookService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getLibrary(params['uuid'])
                .subscribe(library => {
                    this.library = library;
                    this.bookService.getBooksByLibrary(this.library.id)
                        .subscribe(books => {
                            this.libraryBooks = books
                            this.fetchBookTitles();
                        });
                })
        });
    }

    fetchBookTitles(): void {
        this.libraryBooks?.libraryBooks.forEach(libraryBook => {
            this.bookService.getBook(libraryBook.bookId).subscribe(book => {
                this.bookTitles[libraryBook.bookId] = book.title;
            });
        });
    }
}
