import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Authors} from "../../model/authors";
import {UserService} from "../../../user/service/user.service";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  imports: [
    NgIf,
    NgForOf,
    RouterLink,
    PageHeaderComponent
  ],
  styleUrl: './author-list.component.css'
})
export class AuthorListComponent implements OnInit {
  constructor(public userService: UserService, private service: AuthorService) {
  }

  authors: Authors | undefined;

  ngOnInit() {
    this.service.getAuthors().subscribe(authors => this.authors = authors)
  }
}
