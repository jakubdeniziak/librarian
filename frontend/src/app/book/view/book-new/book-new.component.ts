import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {BookForm} from "../../model/book-form";
import {BookService} from "../../service/book.service";
import {v4 as uuid} from "uuid";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {Authors} from "../../../features/author/models/authors.model";
import {AuthorService} from "../../../features/author/services/author.service";
import {PublisherService} from "../../../features/publisher/service/publisher.service";
import {Publishers} from "../../../features/publisher/models/publishers.model";

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
  imports: [
    PageHeaderComponent,
    FormsModule,
    NgIf,
    NgForOf,
    RouterLink
  ],
  styleUrl: './book-new.component.css'
})
export class BookNewComponent implements OnInit {
  uuid: string | undefined;
  book: BookForm | undefined;
  formats: string[] | undefined;
  authors: Authors | undefined;
  publishers: Publishers | undefined;

  constructor(private bookService: BookService,
              private authorService: AuthorService,
              private publisherService: PublisherService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.uuid = uuid();
    this.book = {isbn: "", title: "", description: "", format: "", authorId: "", publisherId: ""}

    this.formats = ['AUDIOBOOK', 'EBOOK', 'HARDCOVER', 'PAPERBACK'];

    this.authorService.getAuthors()
      .subscribe(authors => this.authors = authors)

    this.publisherService.getPublishers()
      .subscribe(publishers => this.publishers = publishers)
  }

  onSubmit(): void {
    this.bookService.putBook(this.uuid!, this.book!)
      .subscribe(() => this.router.navigate(['/books']));
  }
}
