import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {Books} from "@features/book/models/books.model";
import {BookService} from "@features/book/services/book.service";
import {AuthorDetails} from "@features/author/models/author-details.model";
import {UserService} from "@core/auth/services/user.service";
import {AuthorService} from "@features/author/services/author.service";
import {AUTHORS, BOOKS} from "../../../../pages";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css',
  imports: [
    RouterLink,
    PageHeaderComponent
  ]
})
export class AuthorDetailsComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;
  protected readonly BOOKS = BOOKS;

  protected author: AuthorDetails | undefined;
  protected books: Books | undefined;

  constructor(protected userService: UserService, private authorService: AuthorService, private bookService: BookService, private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.authorService.getAuthor(params['uuid']).subscribe(author => this.author = author);
      this.bookService.getBooksByAuthor(params['uuid']).subscribe(books => this.books = books);
    });
  }
}
