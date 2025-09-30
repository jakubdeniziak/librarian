import {Component, OnInit} from '@angular/core';
import {LibraryFormModel} from "@features/library/models/library-form.model";
import {LibraryService} from "@features/library/services/library.service";
import {Router, RouterLink} from "@angular/router";
import {v4 as uuid} from "uuid";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {LIBRARIES} from "../../../../pages";

@Component({
  selector: 'app-library-new',
  templateUrl: './library-new.component.html',
  styleUrl: './library-new.component.css',
  imports: [
    RouterLink,
    FormsModule,
    PageHeaderComponent
  ]
})
export class LibraryNewComponent implements OnInit {
  protected readonly LIBRARIES = LIBRARIES;

  protected uuid: string | undefined;
  protected library: LibraryFormModel | undefined;

  constructor(private libraryService: LibraryService, private router: Router) {
  }

  public ngOnInit(): void {
    this.uuid = uuid();
    this.library = {address: "", description: "", name: ""}
  }

  protected onSubmit(): void {
    this.libraryService.putLibrary(this.uuid!, this.library!)
      .subscribe(() => this.router.navigate([LIBRARIES]));
  }
}
