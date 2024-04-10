import {Component, OnInit} from '@angular/core';
import {AuthorDetails} from "../../model/author-details";
import {AuthorService} from "../../service/author.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrl: './author-edit.component.css'
})
export class AuthorEditComponent implements OnInit {
    uuid: string | undefined;
    author: AuthorDetails | undefined;

    constructor(private service: AuthorService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getAuthor(params['uuid'])
                .subscribe(author => {
                    this.uuid = author.id
                    this.author = author
                });
        });
    }

    onSubmit() {
        this.service.putAuthor(this.uuid!, this.author!)
            .subscribe(() => this.router.navigate(['/authors/' + this.uuid]));
    }
}
