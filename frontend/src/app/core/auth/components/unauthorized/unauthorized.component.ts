import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-unauthorized',
  templateUrl: './unauthorized.component.html',
  styleUrl: './unauthorized.component.css'
})
export class UnauthorizedComponent {
  constructor(private router: Router) {
  }

  protected goHome(): void {
    this.router.navigate(['/']).catch(err => {
      console.error('Navigation failed', err);
    });
  }
}
