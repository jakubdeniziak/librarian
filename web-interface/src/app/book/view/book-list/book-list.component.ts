import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Books} from "../../model/books";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit {
    constructor(private service: BookService) {
    }

    books: Books | undefined;

    ngOnInit() {
        this.service.getBooks().subscribe(books => this.books = books)
    }
}
