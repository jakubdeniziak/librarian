import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Books} from "../../model/books";
import {UserService} from "../../../user/service/user.service";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  imports: [
    PageHeaderComponent,
    NgIf,
    NgForOf,
    RouterLink
  ],
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
