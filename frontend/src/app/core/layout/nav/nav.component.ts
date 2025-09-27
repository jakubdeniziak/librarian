import {Component, ElementRef, HostListener} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {NgOptimizedImage} from "@angular/common";
import {UserService} from "../../../user/service/user.service";
import * as Pages from "../../../pages";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
  imports: [
    RouterLink,
    NgOptimizedImage,
  ]
})
export class NavComponent {
  protected readonly PAGES = Pages;

  public dropdownOpen = false;

  constructor(public userService: UserService, private router: Router, private eRef: ElementRef) {
  }

  public toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
  }

  public logout(): void {
    this.dropdownOpen = false;
    this.userService.logout();
    this.router.navigateByUrl('/').then(success => {
      if (!success) console.error('Logout navigation failed');
    });
  }

  @HostListener('document:click', ['$event'])
  public closeDropdownIfClickedOutside(event: MouseEvent): void {
    const clickedInside = this.eRef.nativeElement.contains(event.target);
    if (!clickedInside) {
      this.dropdownOpen = false;
    }
  }
}
