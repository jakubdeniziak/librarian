import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {PublisherDetails} from "../../models/publisher-details.model";
import {PublisherService} from "../../service/publisher.service";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {PUBLISHERS} from "../../../../pages";

@Component({
  selector: 'app-publisher-edit',
  templateUrl: './publisher-edit.component.html',
  styleUrl: './publisher-edit.component.css',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink,
    PageHeaderComponent
  ]
})
export class PublisherEditComponent implements OnInit {
  protected readonly PUBLISHERS = PUBLISHERS;

  protected uuid: string | undefined;
  protected publisher: PublisherDetails | undefined;

  constructor(private service: PublisherService, private route: ActivatedRoute, private router: Router) {
  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getPublisher(params['uuid'])
        .subscribe(publisher => {
          this.uuid = publisher.id
          this.publisher = publisher
        });
    });
  }

  public onSubmit() {
    this.service.putPublisher(this.uuid!, this.publisher!)
      .subscribe(() => this.router.navigate([PUBLISHERS + '/' + this.uuid]));
  }
}
