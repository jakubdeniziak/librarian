import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PublisherForm} from "../../model/publisher-form";
import {PublisherService} from "../../service/publisher.service";
import {v4 as uuid} from "uuid";

@Component({
  selector: 'app-publisher-new',
  templateUrl: './publisher-new.component.html',
  styleUrl: './publisher-new.component.css'
})
export class PublisherNewComponent implements OnInit {
    uuid: string | undefined;
    publisher: PublisherForm | undefined;

    constructor(private publisherService: PublisherService, private router: Router) {
    }

    ngOnInit(): void {
        this.uuid = uuid();
        this.publisher = {name: "", websiteUrl: "", description: ""}
    }

    onSubmit(): void {
        this.publisherService.putPublisher(this.uuid!, this.publisher!)
            .subscribe(() => this.router.navigate(['/publishers']));
    }
}
