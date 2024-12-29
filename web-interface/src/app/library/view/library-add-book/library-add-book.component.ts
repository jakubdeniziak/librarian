import {Component, OnInit} from '@angular/core';
import {Books} from "../../../book/model/books";
import {LibraryService} from "../../service/library.service";
import {BookService} from "../../../book/service/book.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LibraryBookForm} from "../../model/library-book/library-book-form";

@Component({
  selector: 'app-library-add-book',
  templateUrl: './library-add-book.component.html',
  styleUrl: './library-add-book.component.css'
})
export class LibraryAddBookComponent implements OnInit {
    libraryId: string | undefined;
    bookId: string | undefined;
    books: Books | undefined;
    libraryBook: LibraryBookForm | undefined;


    constructor(private libraryService: LibraryService,
                private bookService: BookService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.libraryService.getLibrary(params['uuid'])
                .subscribe(library => this.libraryId = library.id);
        });

        this.bookService.getBooks().subscribe(books => this.books = books);

        this.libraryBook = {numberOfCopies: 0};
    }

    onSubmit() {
        this.libraryService.addBookToLibrary(this.libraryId!, this.bookId!, this.libraryBook!)
            .subscribe(() => this.router.navigate(['/libraries', this.libraryId!]));
    }
}
