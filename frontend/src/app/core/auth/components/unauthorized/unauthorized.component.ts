import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import * as Pages from "../../../../pages";

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  templateUrl: './unauthorized.component.html',
  styleUrl: './unauthorized.component.css',
  imports: [RouterLink, PageHeaderComponent],
})
export class UnauthorizedComponent {
  protected readonly PAGES = Pages;
}
