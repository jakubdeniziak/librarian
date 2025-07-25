import { Component } from '@angular/core';
import {LoginService} from "../../login/service/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent {

    constructor(public loginService: LoginService, private router: Router) {}

    logout(): void {
        this.loginService.logout();
        this.router.navigate(['/']);
    }

}
