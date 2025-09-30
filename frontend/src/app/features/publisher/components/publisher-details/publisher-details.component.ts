import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {BOOKS, PUBLISHERS} from "../../../../pages";
import {BookService} from "@features/book/services/book.service";
import {Books} from "@features/book/models/books.model";
import {PublisherDetails} from "@features/publisher/models/publisher-details.model";
import {UserService} from "@core/auth/services/user.service";
import {PublisherService} from "@features/publisher/service/publisher.service";

@Component({
  selector: 'app-publisher-details',
  templateUrl: './publisher-details.component.html',
  styleUrl: './publisher-details.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class PublisherDetailsComponent implements OnInit {
  protected readonly BOOKS = BOOKS;
  protected readonly PUBLISHERS = PUBLISHERS;

  protected publisher: PublisherDetails | undefined;
  protected books: Books | undefined;

  constructor(protected userService: UserService,
              private publisherService: PublisherService,
              private bookService: BookService,
              private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.publisherService.getPublisher(params['uuid'])
        .subscribe(publisher => this.publisher = publisher)
      this.bookService.getBooksByPublisher(params['uuid'])
        .subscribe(books => this.books = books)
    });
  }
}
