import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BookForm} from "../../model/book-form";
import {BookService} from "../../service/book.service";
import {v4 as uuid} from "uuid";
import {AuthorService} from "../../../author/service/author.service";
import {PublisherService} from "../../../publisher/service/publisher.service";
import {Authors} from "../../../author/model/authors";
import {Publishers} from "../../../publisher/model/publishers";

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
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
