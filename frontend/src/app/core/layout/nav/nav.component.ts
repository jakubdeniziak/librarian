import {Component, ElementRef, HostListener} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {NgOptimizedImage} from "@angular/common";
import * as Pages from "@app/pages";
import {UserService} from "@core/auth/services/user.service";

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
  public mobileMenuOpen = false;

  constructor(public userService: UserService, private router: Router, private eRef: ElementRef) {
  }

  public toggleDropdown(): void {
    if (!this.dropdownOpen) {
      this.mobileMenuOpen = false;
    }
    this.dropdownOpen = !this.dropdownOpen;
  }

  public toggleMobileMenu(): void {
    if (!this.mobileMenuOpen) {
      this.dropdownOpen = false;
    }
    this.mobileMenuOpen = !this.mobileMenuOpen;
  }

  public closeMenus(): void {
    this.dropdownOpen = false;
    this.mobileMenuOpen = false;
  }

  public get accountLabel(): string {
    return this.userService.getAccountLabel() ?? 'Account';
  }

  public get accountInitial(): string {
    const label = this.accountLabel.trim();
    return label.length > 0 ? label[0].toUpperCase() : '?';
  }

  public logout(): void {
    this.closeMenus();
    this.userService.logout();
    this.router.navigateByUrl('/').then(success => {
      if (!success) console.error('Logout navigation failed');
    });
  }

  @HostListener('document:click', ['$event'])
  public closeDropdownIfClickedOutside(event: MouseEvent): void {
    const clickedInside = this.eRef.nativeElement.contains(event.target);
    if (!clickedInside) {
      this.closeMenus();
    }
  }

  @HostListener('document:keydown.escape')
  public onEscape(): void {
    this.closeMenus();
  }
}
