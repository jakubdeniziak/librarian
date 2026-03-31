import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {Router, RouterLink} from "@angular/router";
import {UserService} from "@core/auth/services/user.service";
import * as Pages from "../../../../pages";

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink
  ]
})
export class RegisterComponent {
  protected readonly PAGES = Pages;

  username = '';
  password = '';
  error = '';

  constructor(private userService: UserService, private router: Router) {
  }

  public register(): void {
    this.userService.register(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => {
        if (err.error && typeof err.error === 'string') {
          this.error = err.error;
        } else {
          this.error = 'Something went wrong. Please try again.';
        }
      }
    });
  }
}
