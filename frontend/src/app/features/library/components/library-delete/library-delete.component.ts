import {Component, OnInit} from '@angular/core';
import {Libraries} from "@features/library/models/libraries";
import {LibraryService} from "@features/library/services/library.service";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {LIBRARIES} from "../../../../pages";

@Component({
  selector: 'app-library-delete',
  templateUrl: './library-delete.component.html',
  styleUrl: './library-delete.component.css',
  imports: [
    RouterLink,
    PageHeaderComponent
  ]
})
export class LibraryDeleteComponent implements OnInit {
  protected readonly LIBRARIES = LIBRARIES;

  protected libraries: Libraries | undefined;

  constructor(private service: LibraryService) {
  }

  public ngOnInit(): void {
    this.service.getLibraries().subscribe(libraries => this.libraries = libraries)
  }

  protected onDeleteButtonClicked(libraryId: string) {
    this.service.deleteLibrary(libraryId).subscribe(() => this.ngOnInit())
  }
}
