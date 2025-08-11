import {Component, OnInit} from '@angular/core';
import {Libraries} from "../../model/libraries";
import {LibraryService} from "../../service/library.service";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-library-list',
  templateUrl: './library-list.component.html',
  imports: [
    PageHeaderComponent,
    NgIf,
    NgForOf,
    RouterLink
  ],
  styleUrl: './library-list.component.css'
})
export class LibraryListComponent implements OnInit {
  constructor(private service: LibraryService) {
  }

  libraries: Libraries | undefined

  ngOnInit(): void {
    this.service.getLibraries().subscribe(libraries => this.libraries = libraries);
  }
}
