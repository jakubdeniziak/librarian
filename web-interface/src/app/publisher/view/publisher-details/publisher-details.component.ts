import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {ActivatedRoute} from "@angular/router";
import {Publisher} from "../../model/publisher";

@Component({
  selector: 'app-publisher-details',
  templateUrl: './publisher-details.component.html',
  styleUrl: './publisher-details.component.css'
})
export class PublisherDetailsComponent implements OnInit {
    constructor(private service: PublisherService, private route: ActivatedRoute) {
    }

    publisher: Publisher | undefined;

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.service.getPublisher(params['uuid'])
                .subscribe(publisher => this.publisher = publisher)
        });
    }
}
