import {Component} from '@angular/core';
import {NavComponent} from "./section/nav/nav.component";
import {RouterOutlet} from "@angular/router";
import {FooterComponent} from "./core/layout/footer/footer.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    NavComponent,
    RouterOutlet,
    FooterComponent
  ],
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'web-interface';
}
