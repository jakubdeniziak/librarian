import {Component, OnInit} from '@angular/core';
import {AuthorForm} from "../../model/author-form";
import {AuthorService} from "../../service/author.service";
import {Router} from "@angular/router";
import {v4 as uuid} from "uuid";

@Component({
  selector: 'app-author-new',
  templateUrl: './author-new.component.html',
  styleUrl: './author-new.component.css'
})
export class AuthorNewComponent implements OnInit {
    uuid: string | undefined;
    author: AuthorForm | undefined;

    constructor(private authorService: AuthorService, private router: Router) {
    }

    ngOnInit(): void {
        this.uuid = uuid();
        this.author = {firstName: "", lastName: "", description: ""}
    }

    onSubmit(): void {
        this.authorService.putAuthor(this.uuid!, this.author!)
            .subscribe(() => this.router.navigate(['/authors']));
    }
}
