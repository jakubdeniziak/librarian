import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {Publishers} from "../../models/publishers.model";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "../../../../user/service/user.service";
import {PUBLISHERS} from "../../../../pages";

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  styleUrl: './publisher-list.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink,
    PageHeaderComponent
  ]
})
export class PublisherListComponent implements OnInit {
  protected readonly PUBLISHERS = PUBLISHERS;

  protected publishers: Publishers | undefined;

  constructor(private publisherService: PublisherService, public userService: UserService) {
  }

  public ngOnInit(): void {
    this.publisherService.getPublishers()
      .subscribe(publishers => this.publishers = publishers);
  }
}
