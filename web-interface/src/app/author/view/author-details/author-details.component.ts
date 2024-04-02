import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Author} from "../../model/author";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css'
})
export class AuthorDetailsComponent implements OnInit {
    author: Author | undefined

    constructor(private service: AuthorService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getAuthor(params['uuid'])
                .subscribe(author => this.author = author)
        });
    }
}
