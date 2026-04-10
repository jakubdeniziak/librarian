import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "@core/auth/services/user.service";

@Component({
  selector: 'app-user',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [
    FormsModule,
    PageHeaderComponent,
    RouterLink,
  ],
})
export class LoginComponent {
  protected username = '';
  protected password = '';
  protected error = '';

  constructor(private loginService: UserService, private router: Router) {
  }

  public login(): void {
    this.loginService.login(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/']),
      error: () => this.error = 'Invalid credentials. Please try again.',
    });
  }
}
