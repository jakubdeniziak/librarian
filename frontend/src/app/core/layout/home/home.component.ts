import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {UserService} from "@core/auth/services/user.service";
import {DashboardComponent} from "@features/dashboard/components/dashboard/dashboard.component";
import * as Pages from "../../../pages";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [RouterLink, DashboardComponent]
})
export class HomeComponent {
  protected readonly PAGES = Pages;

  constructor(
    protected loginService: UserService,
  ) {
  }
}
