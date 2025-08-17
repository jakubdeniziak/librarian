import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../services/author.service";
import {Authors} from "../../models/authors.model";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "../../../../shared/page-header/page-header.component";
import {UserService} from "../../../../user/service/user.service";
import {Author} from "../../models/author.model";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.css',
  imports: [
    RouterLink,
    PageHeaderComponent
  ]
})
export class AuthorListComponent implements OnInit {
  public authors: Authors | undefined;

  constructor(public userService: UserService, private service: AuthorService) {
  }

  public ngOnInit() {
    this.service.getAuthors().subscribe(authors => {
      this.authors = authors;
    })
  }

  public getAuthorLink(author: Author): string {
    return `/authors/${author.id}`
  }
}
