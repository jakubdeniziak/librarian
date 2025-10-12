import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "@core/auth/services/user.service";
import {AUTHORS} from "../../../../pages";
import {AuthorService} from "@features/author/services/author.service";
import {Authors} from "@features/author/models/authors.model";
import {KeyValue, KeyValuePipe} from "@angular/common";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.css',
  imports: [
    RouterLink,
    PageHeaderComponent,
    KeyValuePipe
  ]
})
export class AuthorListComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;

  protected authors: Authors | undefined;
  protected authorsGrouped: { [letter: string]: Authors } = {};

  constructor(protected userService: UserService, private authorService: AuthorService) {
  }

  public ngOnInit() {
    this.loadAuthors();
  }

  protected onDelete(authorId: string) {
    this.authorService.deleteAuthor(authorId).subscribe(() => {
      this.loadAuthors();
    });
  }

  protected alphabetical(a: KeyValue<string, Authors>, b: KeyValue<string, Authors>) {
    return a.key.localeCompare(b.key);
  }

  private loadAuthors(): void {
    this.authorService.getAuthors().subscribe(authors => {
      this.authors = authors;
      this.authorsGrouped = this.groupAuthorsBySurname(authors);
    });
  }

  private groupAuthorsBySurname(authors: Authors): { [letter: string]: Authors } {
    return authors.authors.reduce(
      (groups: { [letter: string]: Authors }, author) => {
        const letter = author.lastName.charAt(0).toUpperCase();
        if (!groups[letter]) {
          groups[letter] = {authors: [], count: 0};
        }
        groups[letter].authors.push(author);
        groups[letter].count = groups[letter].authors.length;
        return groups;
      },
      {} as { [letter: string]: Authors }
    );
  }
}
