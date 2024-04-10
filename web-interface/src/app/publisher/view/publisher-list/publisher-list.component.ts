import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {Publishers} from "../../model/publishers";

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  styleUrl: './publisher-list.component.css'
})
export class PublisherListComponent implements OnInit {
    constructor(private service: PublisherService) {
    }

    publishers: Publishers | undefined;

    ngOnInit(): void {
        this.service.getPublishers().subscribe(publishers => this.publishers = publishers);
    }
}
