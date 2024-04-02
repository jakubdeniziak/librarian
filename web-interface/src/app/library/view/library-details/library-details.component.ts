import {Component, OnInit} from '@angular/core';
import {LibraryService} from "../../service/library.service";
import {ActivatedRoute} from "@angular/router";
import {LibraryDetails} from "../../model/library-details";

@Component({
  selector: 'app-library-details',
  templateUrl: './library-details.component.html',
  styleUrl: './library-details.component.css'
})
export class LibraryDetailsComponent implements OnInit {
    constructor(private service: LibraryService, private route: ActivatedRoute) {
    }

    library: LibraryDetails | undefined

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getLibrary(params['uuid'])
                .subscribe(library => this.library = library)
        });
    }
}
