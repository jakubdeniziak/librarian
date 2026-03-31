import {Component} from '@angular/core';
import {RouterLink} from '@angular/router';
import {PageHeaderComponent} from '@shared/components/page-header/page-header.component';
import * as Pages from '../../../pages';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink, PageHeaderComponent],
  templateUrl: './not-found.component.html',
})
export class NotFoundComponent {
  protected readonly PAGES = Pages;
}
