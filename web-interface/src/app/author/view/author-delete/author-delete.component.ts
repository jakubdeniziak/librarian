import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Authors} from "../../model/authors";

@Component({
  selector: 'app-author-delete',
  templateUrl: './author-delete.component.html',
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
