import {Component, OnInit} from '@angular/core';
import {LibraryForm} from "../../model/library-form";
import {LibraryService} from "../../service/library.service";
import {Router, RouterLink} from "@angular/router";
import {v4 as uuid} from "uuid";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";

@Component({
  selector: 'app-library-new',
  templateUrl: './library-new.component.html',
  imports: [
    RouterLink,
    FormsModule,
    PageHeaderComponent,
    NgIf
  ],
  styleUrl: './library-new.component.css'
})
export class LibraryNewComponent implements OnInit {
  uuid: string | undefined;
  library: LibraryForm | undefined;

  constructor(private libraryService: LibraryService, private router: Router) {
  }

  ngOnInit(): void {
    this.uuid = uuid();
    this.library = {address: "", description: "", name: ""}
  }

  onSubmit(): void {
    this.libraryService.putLibrary(this.uuid!, this.library!)
      .subscribe(() => this.router.navigate(['/libraries']));
  }
}
