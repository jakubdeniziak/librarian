import {Component, OnInit} from '@angular/core';
import {AuthorDetails} from "../../models/author-details.model";
import {AuthorService} from "../../services/author.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {AUTHORS} from "../../../../pages";
import {AuthorForm} from "@features/author/models/author-form.model";
import {RichTextEditorComponent} from "@shared/components/rich-text-editor/rich-text-editor.component";

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrl: './author-edit.component.css',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink,
    RichTextEditorComponent,
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
    const payload: AuthorForm = {
      firstName: this.author!.firstName,
      lastName: this.author!.lastName,
      description: this.author!.description,
      pictureUrl: this.author!.pictureUrl,
      aliases: this.author!.aliases,
      genres: this.author!.genres,
      birthDate: this.author!.birthDate,
      deathDate: this.author!.deathDate,
    };

    this.service.putAuthor(this.uuid!, payload).subscribe(() => {
      this.router.navigateByUrl(`${AUTHORS}/${this.uuid}`).then(success => {
        if (!success) console.error('Edit submit navigation failed');
      })
    });
  }

  protected parseCommaList(value: string): string[] {
    return (value ?? '')
      .split(',')
      .map(s => s.trim())
      .filter(Boolean);
  }
}
