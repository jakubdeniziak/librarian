import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {BookDetails} from "../../model/book-details";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {Authors} from "../../../features/author/models/authors.model";
import {AuthorService} from "../../../features/author/services/author.service";
import {Publishers} from "../../../features/publisher/models/publishers.model";
import {PublisherService} from "../../../features/publisher/service/publisher.service";

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  imports: [
    PageHeaderComponent,
    FormsModule,
    NgIf,
    NgForOf,
    RouterLink
  ],
  styleUrl: './book-edit.component.css'
})
export class BookEditComponent implements OnInit {
  uuid: string | undefined;
  book: BookDetails | undefined;
  authors: Authors | undefined;
  publishers: Publishers | undefined;

  private publisherService: PublisherService;

  constructor(private bookService: BookService,
              private authorService: AuthorService,
              publisherService: PublisherService,
              private route: ActivatedRoute,
              private router: Router) {
    this.publisherService = publisherService;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.bookService.getBook(params['uuid'])
        .subscribe(book => {
          this.uuid = book.id
          this.book = book
        });
    });

    this.authorService.getAuthors()
      .subscribe(authors => this.authors = authors)

    this.publisherService.getPublishers()
      .subscribe(publishers => this.publishers = publishers)
  }

  onSubmit(): void {
    this.bookService.putBook(this.uuid!, this.book!)
      .subscribe(() => this.router.navigate(['/books/' + this.uuid]));
  }
}
