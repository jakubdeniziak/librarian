import {Component, OnInit} from '@angular/core';
import {BookService} from "@features/book/services/book.service";
import {Books} from "@features/book/models/books.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {RouterLink} from "@angular/router";
import {BOOKS} from "@app/pages";
import {UserService} from "@core/auth/services/user.service";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class BookListComponent implements OnInit {
  protected readonly BOOKS = BOOKS;

  protected books: Books | undefined;

  constructor(public userService: UserService, private service: BookService) {
  }

  public ngOnInit() {
    this.service.getBooks().subscribe(books => this.books = books)
  }
}
