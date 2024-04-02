import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {ActivatedRoute} from "@angular/router";
import {AuthorDetails} from "../../model/author-details";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css'
})
export class AuthorDetailsComponent implements OnInit {
    author: AuthorDetails | undefined

    constructor(private service: AuthorService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getAuthor(params['uuid'])
                .subscribe(author => this.author = author)
        });
    }
}
