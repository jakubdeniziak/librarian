import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {PUBLISHERS} from "@app/pages";
import {UserService} from "@core/auth/services/user.service";
import {Publishers} from "@features/publisher/models/publishers.model";
import {PublisherService} from "@features/publisher/service/publisher.service";

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
