import {Component, OnInit} from '@angular/core';
import {AuthorForm} from "../../model/author-form";
import {AuthorService} from "../../service/author.service";
import {Router, RouterLink} from "@angular/router";
import {v4 as uuid} from "uuid";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-author-new',
  templateUrl: './author-new.component.html',
  imports: [
    PageHeaderComponent,
    FormsModule,
    RouterLink,
    NgIf
  ],
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
