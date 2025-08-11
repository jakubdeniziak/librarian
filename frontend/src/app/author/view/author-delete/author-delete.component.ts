import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Authors} from "../../model/authors";
import {NgForOf, NgIf} from "@angular/common";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-author-delete',
  templateUrl: './author-delete.component.html',
  imports: [
    NgIf,
    NgForOf,
    PageHeaderComponent,
    RouterLink
  ],
  styleUrl: './author-delete.component.css'
})
export class AuthorDeleteComponent implements OnInit {
  authors: Authors | undefined;

  constructor(private service: AuthorService) {
  }

  onDeleteButtonClicked(authorId: string) {
    this.service.deleteAuthor(authorId).subscribe(() => this.ngOnInit())
  }

  ngOnInit() {
    this.service.getAuthors().subscribe(authors => this.authors = authors)
  }
}
