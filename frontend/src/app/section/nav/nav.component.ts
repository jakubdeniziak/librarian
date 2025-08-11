import {Component, ElementRef, HostListener, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../user/service/user.service";
import {Router, RouterLink} from "@angular/router";
import {NgIf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  imports: [
    RouterLink,
    NgOptimizedImage,
    NgIf
  ],
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit, OnDestroy {

  dropdownOpen = false;

  constructor(public userService: UserService, private router: Router, private eRef: ElementRef) {
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
  }

  toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent): void {
    if (!this.eRef.nativeElement.contains(event.target)) {
      this.dropdownOpen = false;
    }
  }

  logout(): void {
    this.dropdownOpen = false;
    this.userService.logout();
    this.router.navigate(['/']);
  }

}
