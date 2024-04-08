import {Component, OnInit} from '@angular/core';
import {Publishers} from "../../model/publishers";
import {PublisherService} from "../../service/publisher.service";

@Component({
  selector: 'app-publisher-delete',
  templateUrl: './publisher-delete.component.html',
  styleUrl: './publisher-delete.component.css'
})
export class PublisherDeleteComponent implements OnInit {
    publishers: Publishers | undefined;

    constructor(private service: PublisherService) {
    }

    onDeleteButtonClicked(publisherId: string) {
        this.service.deletePublisher(publisherId).subscribe(() => this.ngOnInit())
    }

    ngOnInit() {
        this.service.getPublishers().subscribe(publishers => this.publishers = publishers)
    }
}
