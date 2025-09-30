import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {PublisherDetails} from "../../models/publisher-details.model";
import {Books} from "../../../../book/model/books";
import {UserService} from "../../../../user/service/user.service";
import {BookService} from "../../../../book/service/book.service";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {BOOKS, PUBLISHERS} from "../../../../pages";

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
