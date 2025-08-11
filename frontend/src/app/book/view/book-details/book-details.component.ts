import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {PublisherService} from "../../../publisher/service/publisher.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthorService} from "../../../author/service/author.service";
import {BookDetails} from "../../model/book-details";
import {AuthorDetails} from "../../../author/model/author-details";
import {PublisherDetails} from "../../../publisher/model/publisher-details";
import {UserService} from "../../../user/service/user.service";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  imports: [
    PageHeaderComponent,
    NgIf,
    RouterLink
  ],
  styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit {
  book: BookDetails | undefined;
  author: AuthorDetails | undefined;
  publisher: PublisherDetails | undefined;

  constructor(public userService: UserService,
              private bookService: BookService,
              private authorService: AuthorService,
              private publisherService: PublisherService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
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
