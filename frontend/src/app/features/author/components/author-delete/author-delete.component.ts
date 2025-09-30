import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../services/author.service";
import {Authors} from "../../models/authors.model";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {Author} from "../../models/author.model";

@Component({
  selector: 'app-author-delete',
  templateUrl: './author-delete.component.html',
  styleUrl: './author-delete.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class AuthorDeleteComponent implements OnInit {
  public authors: Authors | undefined;

  constructor(private service: AuthorService) {
  }

  public ngOnInit(): void {
    this.loadAuthors();
  }

  public onDeleteButtonClicked(authorId: string) {
    this.service.deleteAuthor(authorId).subscribe(() => {
      this.loadAuthors();
    });
  }

  public getAuthorLink(author: Author): string {
    return `/authors/${author.id}`;
  }

  private loadAuthors(): void {
    this.service.getAuthors().subscribe(authors => this.authors = authors);
  }
}
