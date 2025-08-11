import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthorDetails} from "../../model/author-details";
import {BookService} from "../../../book/service/book.service";
import {Books} from "../../../book/model/books";
import {UserService} from "../../../user/service/user.service";
import {NgForOf, NgIf} from "@angular/common";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  imports: [
    NgIf,
    NgForOf,
    RouterLink,
    PageHeaderComponent
  ],
  styleUrl: './author-details.component.css'
})
export class AuthorDetailsComponent implements OnInit {
  author: AuthorDetails | undefined
  books: Books | undefined

  constructor(public userService: UserService, private authorService: AuthorService, private bookService: BookService, private route: ActivatedRoute) {
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
