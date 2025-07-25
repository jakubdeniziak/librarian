import {Component} from '@angular/core';
import {LoginService} from "../service/login.service";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {PageHeaderComponent} from "../../shared/page-header/page-header.component";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    imports: [
        FormsModule,
        NgIf,
        PageHeaderComponent,
    ],
    standalone: true
})
export class LoginComponent {
    username = '';
    password = '';
    error = '';

    constructor(private loginService: LoginService, private router: Router) {}

    login(): void {
        this.loginService.login(this.username, this.password).subscribe({
            next: () => this.router.navigate(['/']),
            error: () => this.error = 'Invalid credentials. Please try again.',
        });
    }
}
