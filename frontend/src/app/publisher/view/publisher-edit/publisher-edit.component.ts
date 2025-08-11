import {Component} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {PublisherDetails} from "../../model/publisher-details";
import {PublisherService} from "../../service/publisher.service";
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "../../../shared/page-header/page-header.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-publisher-edit',
  templateUrl: './publisher-edit.component.html',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink,
    NgIf
  ],
  styleUrl: './publisher-edit.component.css'
})
export class PublisherEditComponent {
  uuid: string | undefined;
  publisher: PublisherDetails | undefined;

  constructor(private service: PublisherService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getPublisher(params['uuid'])
        .subscribe(publisher => {
          this.uuid = publisher.id
          this.publisher = publisher
        });
    });
  }

  onSubmit() {
    this.service.putPublisher(this.uuid!, this.publisher!)
      .subscribe(() => this.router.navigate(['/publishers/' + this.uuid]));
  }
}
