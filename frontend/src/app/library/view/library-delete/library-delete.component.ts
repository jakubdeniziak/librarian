import {Component, OnInit} from '@angular/core';
import {Libraries} from "../../model/libraries";
import {LibraryService} from "../../service/library.service";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-library-delete',
  templateUrl: './library-delete.component.html',
  imports: [
    RouterLink,
    PageHeaderComponent,
    NgIf,
    NgForOf
  ],
  styleUrl: './library-delete.component.css'
})
export class LibraryDeleteComponent implements OnInit {
  libraries: Libraries | undefined;

  constructor(private service: LibraryService) {
  }

  ngOnInit(): void {
    this.service.getLibraries().subscribe(libraries => this.libraries = libraries)
  }

  onDeleteButtonClicked(libraryId: string) {
    this.service.deleteLibrary(libraryId).subscribe(() => this.ngOnInit())
  }


}
