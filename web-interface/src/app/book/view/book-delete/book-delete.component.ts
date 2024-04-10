import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Books} from "../../model/books";

@Component({
  selector: 'app-book-delete',
  templateUrl: './book-delete.component.html',
  styleUrl: './book-delete.component.css'
})
export class BookDeleteComponent implements OnInit {
    books: Books | undefined;

    constructor(private service: BookService) {
    }

    onDeleteButtonClicked(bookId: string) {
        this.service.deleteBook(bookId).subscribe(() => this.ngOnInit())
    }

    ngOnInit() {
        this.service.getBooks().subscribe(books => this.books = books)
    }
}
