import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {ActivatedRoute} from "@angular/router";
import {PublisherDetails} from "../../model/publisher-details";
import {Books} from "../../../book/model/books";
import {BookService} from "../../../book/service/book.service";

@Component({
  selector: 'app-publisher-details',
  templateUrl: './publisher-details.component.html',
  styleUrl: './publisher-details.component.css'
})
export class PublisherDetailsComponent implements OnInit {
    publisher: PublisherDetails | undefined;
    books: Books | undefined;

    constructor(private publisherService: PublisherService, private bookService: BookService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.publisherService.getPublisher(params['uuid'])
                .subscribe(publisher => this.publisher = publisher)

            this.bookService.getBooksByPublisher(params['uuid'])
                .subscribe(books => this.books = books)
        });
    }
}
