import {Component, OnInit} from '@angular/core';
import {BookService} from "@features/book/services/book.service";
import {Books} from "@features/book/models/books.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {RouterLink} from "@angular/router";
import {BOOKS} from "@app/pages";

@Component({
  selector: 'app-book-delete',
  templateUrl: './book-delete.component.html',
  styleUrl: './book-delete.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class BookDeleteComponent implements OnInit {
  protected readonly BOOKS = BOOKS;

  protected books: Books | undefined;

  constructor(private service: BookService) {
  }

  public ngOnInit() {
    this.service.getBooks()
      .subscribe(books => this.books = books)
  }

  protected onDeleteButtonClicked(bookId: string) {
    this.service.deleteBook(bookId).subscribe(() => this.ngOnInit())
  }
}
