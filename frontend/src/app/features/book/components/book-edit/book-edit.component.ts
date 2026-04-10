import {Component, OnInit} from '@angular/core';
import {BookService} from "@features/book/services/book.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {BookDetails} from "@features/book/models/book-details.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {BOOKS} from "@app/pages";
import {Authors} from "@features/author/models/authors.model";
import {Publishers} from "@features/publisher/models/publishers.model";
import {PublisherService} from "@features/publisher/service/publisher.service";
import {AuthorService} from "@features/author/services/author.service";

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  imports: [
    PageHeaderComponent,
    FormsModule,
    RouterLink
  ],
  styleUrl: './book-edit.component.css'
})
export class BookEditComponent implements OnInit {
  protected readonly BOOKS = BOOKS;

  protected uuid: string | undefined;
  protected book: BookDetails | undefined;
  protected authors: Authors | undefined;
  protected publishers: Publishers | undefined;

  constructor(private bookService: BookService,
              private authorService: AuthorService,
              private publisherService: PublisherService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public ngOnInit(): void {
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

  protected onSubmit(): void {
    this.bookService.putBook(this.uuid!, this.book!)
      .subscribe(() => this.router.navigate([BOOKS, this.uuid]));
  }
}
