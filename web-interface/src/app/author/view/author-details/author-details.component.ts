import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {ActivatedRoute} from "@angular/router";
import {AuthorDetails} from "../../model/author-details";
import {BookService} from "../../../book/service/book.service";
import {Books} from "../../../book/model/books";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css'
})
export class AuthorDetailsComponent implements OnInit {
    author: AuthorDetails | undefined
    books: Books | undefined

    constructor(private authorService: AuthorService, private bookService: BookService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.authorService.getAuthor(params['uuid'])
                .subscribe(author => this.author = author);

            this.bookService.getBooksByAuthor(params['uuid'])
                .subscribe(books => this.books = books)
        });
    }
}
