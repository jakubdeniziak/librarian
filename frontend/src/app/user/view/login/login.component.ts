import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {UserService} from "../../service/user.service";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";

@Component({
  selector: 'app-user',
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

  constructor(private loginService: UserService, private router: Router) {
  }

  login(): void {
    this.loginService.login(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/']),
      error: () => this.error = 'Invalid credentials. Please try again.',
    });
  }
}
