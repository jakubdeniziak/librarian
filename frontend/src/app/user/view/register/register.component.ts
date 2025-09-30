import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    PageHeaderComponent,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username = '';
  password = '';
  error = '';

  constructor(private userService: UserService, private router: Router) {
  }

  register(): void {
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
