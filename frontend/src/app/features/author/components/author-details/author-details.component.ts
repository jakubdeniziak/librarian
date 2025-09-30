import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../services/author.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthorDetails} from "../../models/author-details.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {Books} from "@features/book/models/books.model";
import {UserService} from "../../../../user/service/user.service";
import {BookService} from "@features/book/services/book.service";
import {Book} from "@features/book/models/book.model";

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
  public author: AuthorDetails | undefined;
  public books: Books | undefined;

  constructor(public userService: UserService, private authorService: AuthorService, private bookService: BookService, private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.authorService.getAuthor(params['uuid']).subscribe(author => this.author = author);
      this.bookService.getBooksByAuthor(params['uuid']).subscribe(books => this.books = books);
    });
  }

  public getBookLink(book: Book): string {
    return `/books/${book.id}`;
  }

  public getAuthorEditLink(author: AuthorDetails): string {
    return `/authors/${author?.id}/edit`;
  }
}
