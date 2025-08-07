import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Books} from "../../model/books";
import {UserService} from "../../../user/service/user.service";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit {
  constructor(public userService: UserService, private service: BookService) {
  }

  books: Books | undefined;

  ngOnInit() {
    this.service.getBooks().subscribe(books => this.books = books)
  }
}
