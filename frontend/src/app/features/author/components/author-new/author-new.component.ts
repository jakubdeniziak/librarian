import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {v4 as uuid} from "uuid";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {AUTHORS} from "../../../../pages";
import {AuthorForm} from "@features/author/models/author-form.model";
import {AuthorService} from "@features/author/services/author.service";
import {RichTextEditorComponent} from "@shared/components/rich-text-editor/rich-text-editor.component";

@Component({
  selector: 'app-author-new',
  templateUrl: './author-new.component.html',
  styleUrl: './author-new.component.css',
  imports: [
    PageHeaderComponent,
    FormsModule,
    RouterLink,
    RichTextEditorComponent,
  ]
})
export class AuthorNewComponent implements OnInit {
  protected readonly AUTHORS = AUTHORS;

  protected uuid: string | undefined;
  protected author: AuthorForm | undefined;

  constructor(private authorService: AuthorService, private router: Router) {
  }

  public ngOnInit(): void {
    this.uuid = uuid();
    this.author = {
      firstName: '',
      lastName: '',
      description: '',
      pictureUrl: '',
      aliases: [],
      genres: [],
      birthDate: '',
      deathDate: ''
    };
  }

  public onSubmit(): void {
    this.authorService.putAuthor(this.uuid!, this.author!).subscribe(() => {
      this.router.navigate([AUTHORS, this.uuid]).then(success => {
        if (!success) console.error('Edit submit navigation failed');
      });
    });
  }

  protected parseCommaList(value: string): string[] {
    return (value ?? '')
      .split(',')
      .map(s => s.trim())
      .filter(Boolean);
  }
}
