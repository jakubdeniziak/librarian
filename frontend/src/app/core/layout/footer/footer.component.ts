import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import * as Pages from "../../../pages";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  imports: [
    RouterLink,
    NgOptimizedImage
  ]
})
export class FooterComponent {
  protected readonly PAGES = Pages;
}
