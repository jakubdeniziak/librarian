import {Component, OnInit} from '@angular/core';
import {Libraries} from "../../model/libraries";
import {LibraryService} from "../../service/library.service";

@Component({
  selector: 'app-library-delete',
  templateUrl: './library-delete.component.html',
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
