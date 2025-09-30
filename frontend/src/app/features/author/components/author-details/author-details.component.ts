import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {Books} from "@features/book/models/books.model";
import {BookService} from "@features/book/services/book.service";
import {Book} from "@features/book/models/book.model";
import {AuthorDetails} from "@features/author/models/author-details.model";
import {UserService} from "@core/auth/services/user.service";
import {AuthorService} from "@features/author/services/author.service";

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
