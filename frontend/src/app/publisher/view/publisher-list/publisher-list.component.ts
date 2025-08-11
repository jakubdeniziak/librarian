import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {Publishers} from "../../model/publishers";
import {UserService} from "../../../user/service/user.service";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  imports: [
    PageHeaderComponent,
    NgIf,
    NgForOf,
    RouterLink
  ],
  styleUrl: './publisher-list.component.css'
})
export class PublisherListComponent implements OnInit {
  constructor(public userService: UserService, private service: PublisherService) {
  }

  publishers: Publishers | undefined;

  ngOnInit(): void {
    this.service.getPublishers().subscribe(publishers => this.publishers = publishers);
  }
}
