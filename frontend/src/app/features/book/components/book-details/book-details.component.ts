import {Component, OnInit} from '@angular/core';
import {BookService} from "@features/book/services/book.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {BookDetails} from "@features/book/models/book-details.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {AUTHORS, BOOKS, PUBLISHERS} from "../../../../pages";
import {AuthorDetails} from "@features/author/models/author-details.model";
import {PublisherDetails} from "@features/publisher/models/publisher-details.model";
import {UserService} from "../../../../user/service/user.service";
import {AuthorService} from "@features/author/services/author.service";
import {PublisherService} from "@features/publisher/service/publisher.service";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  imports: [
    PageHeaderComponent,
    RouterLink
  ],
  styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;
  protected readonly PUBLISHERS = PUBLISHERS;
  protected readonly BOOKS = BOOKS;

  protected book: BookDetails | undefined;
  protected author: AuthorDetails | undefined;
  protected publisher: PublisherDetails | undefined;

  constructor(public userService: UserService,
              private bookService: BookService,
              private authorService: AuthorService,
              private publisherService: PublisherService,
              private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.bookService.getBook(params['uuid']).subscribe(book => {
        this.book = book
        this.authorService.getAuthor(this.book.authorId)
          .subscribe(author => this.author = author)
        this.publisherService.getPublisher(this.book.publisherId)
          .subscribe(publisher => this.publisher = publisher)
      })
    });
  }
}
