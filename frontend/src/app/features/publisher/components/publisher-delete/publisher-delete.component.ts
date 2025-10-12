import {Component, OnInit} from '@angular/core';
import {PublisherService} from "../../service/publisher.service";
import {Publishers} from "../../models/publishers.model";
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {PUBLISHERS} from "../../../../pages";

@Component({
  selector: 'app-publisher-delete',
  templateUrl: './publisher-delete.component.html',
  styleUrl: './publisher-delete.component.css',
  imports: [
    PageHeaderComponent,
    RouterLink
  ]
})
export class PublisherDeleteComponent implements OnInit {
  protected readonly PUBLISHERS = PUBLISHERS;
  protected publishers: Publishers | undefined;

  constructor(private service: PublisherService) {
  }

  public ngOnInit() {
    this.loadPublishers();
  }

  public onDeleteButtonClicked(publisherId: string) {
    this.service.deletePublisher(publisherId).subscribe(() => {
      this.loadPublishers();
    });
  }

  private loadPublishers(): void {
    this.service.getPublishers().subscribe(publishers => this.publishers = publishers);
  }
}
