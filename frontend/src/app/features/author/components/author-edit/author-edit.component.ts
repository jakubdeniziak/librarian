import {Component, OnInit} from '@angular/core';
import {AuthorDetails} from "../../models/author-details.model";
import {AuthorService} from "../../services/author.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {AUTHORS} from "../../../../pages";

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrl: './author-edit.component.css',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink
  ]
})
export class AuthorEditComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;

  public uuid: string | undefined;
  public author: AuthorDetails | undefined;

  constructor(private service: AuthorService, private route: ActivatedRoute, private router: Router) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getAuthor(params['uuid']).subscribe(author => {
        this.uuid = author.id;
        this.author = author;
      });
    });
  }

  public onSubmit() {
    this.service.putAuthor(this.uuid!, this.author!).subscribe(() => {
      this.router.navigateByUrl(`${AUTHORS}/${this.uuid}`).then(success => {
        if (!success) console.error('Edit submit navigation failed');
      })
    });
  }
}
