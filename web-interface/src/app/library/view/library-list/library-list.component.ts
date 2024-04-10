import {Component, OnInit} from '@angular/core';
import {Libraries} from "../../model/libraries";
import {LibraryService} from "../../service/library.service";

@Component({
  selector: 'app-library-list',
  templateUrl: './library-list.component.html',
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
