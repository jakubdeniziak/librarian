import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Books} from "@features/book/models/books.model";
import {BookService} from "@features/book/services/book.service";
import {AuthorDetails} from "@features/author/models/author-details.model";
import {UserService} from "@core/auth/services/user.service";
import {AuthorService} from "@features/author/services/author.service";
import {AUTHORS, BOOKS} from "../../../../pages";
import {DatePipe, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css',
  imports: [
    RouterLink,
    NgOptimizedImage,
    DatePipe
  ]
})
export class AuthorDetailsComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;
  protected readonly BOOKS = BOOKS;

  protected author: AuthorDetails | undefined;
  protected books: Books | undefined;

  constructor(protected userService: UserService,
              private authorService: AuthorService,
              private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.authorService.getAuthor(params['uuid']).subscribe(author => this.author = author);
      this.bookService.getBooksByAuthor(params['uuid']).subscribe(books => this.books = books);
    });
  }

  protected onDelete(authorId: string): void {
    this.authorService.deleteAuthor(authorId).subscribe(() => {
      this.router.navigate([AUTHORS]).then(success => {
        if (!success) console.error('Navigation to /authors failed');
      });
    });
  }
}
