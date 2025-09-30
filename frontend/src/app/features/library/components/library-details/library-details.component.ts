import {Component, OnInit} from '@angular/core';
import {LibraryService} from "@features/library/services/library.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {LibraryDetailsModel} from "@features/library/models/library-details.model";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {LIBRARIES} from "../../../../pages";
import {LibraryBookList} from "@features/library-book/components/library-book-list/library-book-list.component";

@Component({
  selector: 'app-library-details',
  templateUrl: './library-details.component.html',
  styleUrl: './library-details.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink,
    LibraryBookList
  ]
})
export class LibraryDetailsComponent implements OnInit {
  protected readonly LIBRARIES = LIBRARIES;

  protected library: LibraryDetailsModel | undefined

  constructor(private service: LibraryService, private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getLibrary(params['uuid'])
        .subscribe(library => {
          this.library = library;
        })
    });
  }
}
