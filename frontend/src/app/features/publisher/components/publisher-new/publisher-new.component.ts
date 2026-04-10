import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {PublisherForm} from "../../models/publisher-form.model";
import {PublisherService} from "../../service/publisher.service";
import {v4 as uuid} from "uuid";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {PUBLISHERS} from "@app/pages";

@Component({
  selector: 'app-publisher-new',
  templateUrl: './publisher-new.component.html',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink,
    PageHeaderComponent
  ],
  styleUrl: './publisher-new.component.css'
})
export class PublisherNewComponent implements OnInit {
  protected readonly PUBLISHERS = PUBLISHERS;

  protected uuid: string | undefined;
  protected publisher: PublisherForm | undefined;

  constructor(private publisherService: PublisherService, private router: Router) {
  }

  public ngOnInit(): void {
    this.uuid = uuid();
    this.publisher = {name: "", websiteUrl: "", description: ""}
  }

  public onSubmit(): void {
    this.publisherService.putPublisher(this.uuid!, this.publisher!)
      .subscribe(() => this.router.navigate([PUBLISHERS]));
  }
}
