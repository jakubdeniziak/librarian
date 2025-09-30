import {Component, OnInit} from '@angular/core';
import {LibrariesModel} from "@features/library/models/libraries.model";
import {LibraryService} from "@features/library/services/library.service";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {RouterLink} from "@angular/router";
import {LIBRARIES} from "../../../../pages";

@Component({
  selector: 'app-library-list',
  templateUrl: './library-list.component.html',
  styleUrl: './library-list.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class LibraryListComponent implements OnInit {
  protected readonly LIBRARIES = LIBRARIES;

  protected libraries: LibrariesModel | undefined

  constructor(private service: LibraryService) {
  }

  public ngOnInit(): void {
    this.service.getLibraries().subscribe(libraries => this.libraries = libraries);
  }
}
