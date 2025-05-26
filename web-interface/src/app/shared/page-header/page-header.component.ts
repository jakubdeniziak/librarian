import {Component, Input} from '@angular/core';
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-page-header',
    templateUrl: './page-header.component.html',
    standalone: true,
    imports: [
        NgIf
    ],
    styleUrl: './page-header.component.css'
})
export class PageHeaderComponent {
    @Input() title?: string = '';
    @Input() subtitle?: string;
    @Input() titleLink?: string;
}
