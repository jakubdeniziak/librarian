import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "@core/auth/services/user.service";
import {AUTHORS} from "../../../../pages";
import {AuthorService} from "@features/author/services/author.service";
import {Authors} from "@features/author/models/authors.model";

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
  protected readonly AUTHORS = AUTHORS;

  protected authors: Authors | undefined;
  protected authorCount: number = 0;

  constructor(public userService: UserService, private service: AuthorService) {
  }

  public ngOnInit() {
    this.service.getAuthors().subscribe(authors => {
      this.authors = authors;
      this.authorCount = authors.authors.length;
    })
  }
}
