import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Authors} from "../../model/authors";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.css'
})
export class AuthorListComponent implements OnInit {
    constructor(private service: AuthorService) {
    }

    authors: Authors | undefined;

    ngOnInit() {
        this.service.getAuthors().subscribe(authors => this.authors = authors)
    }
}
