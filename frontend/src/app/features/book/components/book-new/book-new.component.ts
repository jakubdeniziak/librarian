import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {BookForm} from "@features/book/models/book-form.model";
import {BookService} from "@features/book/services/book.service";
import {v4 as uuid} from "uuid";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {BOOKS} from "../../../../pages";
import {Authors} from "@features/author/models/authors.model";
import {Publishers} from "@features/publisher/models/publishers.model";
import {AuthorService} from "@features/author/services/author.service";
import {PublisherService} from "@features/publisher/service/publisher.service";

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
  styleUrl: './book-new.component.css',
  imports: [
    PageHeaderComponent,
    FormsModule,
    RouterLink
  ]
})
export class BookNewComponent implements OnInit {
  protected readonly BOOKS = BOOKS;

  protected uuid: string | undefined;
  protected book: BookForm | undefined;
  protected formats: string[] | undefined;
  protected authors: Authors | undefined;
  protected publishers: Publishers | undefined;

  constructor(private bookService: BookService,
              private authorService: AuthorService,
              private publisherService: PublisherService,
              private router: Router) {
  }

  public ngOnInit(): void {
    this.uuid = uuid();
    this.book = {isbn: "", title: "", description: "", format: "", authorId: "", publisherId: ""}
    this.formats = ['AUDIOBOOK', 'EBOOK', 'HARDCOVER', 'PAPERBACK'];
    this.authorService.getAuthors()
      .subscribe(authors => this.authors = authors)
    this.publisherService.getPublishers()
      .subscribe(publishers => this.publishers = publishers)
  }

  protected onSubmit(): void {
    this.bookService.putBook(this.uuid!, this.book!)
      .subscribe(() => this.router.navigate([BOOKS]));
  }
}
