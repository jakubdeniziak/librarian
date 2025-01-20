import {Component, OnInit} from '@angular/core';
import {LoginService} from "../service/login.service";
import {Users} from "../model/Users";
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    imports: [
        FormsModule,
        NgForOf
    ],
    standalone: true
})
export class LoginComponent implements OnInit {
    users: Users | undefined;
    selectedUserId: string = '';

    constructor(private loginService: LoginService) {}

    ngOnInit(): void {
        this.loginService.getUsers().subscribe({
            next: (users) => {
                this.users = users;
            },
            error: (error) => {
                console.error('Failed to fetch users:', error);
                alert('Failed to load user list. Please try again later.');
            },
        });
    }

    login(): void {
        if (this.selectedUserId) {
            this.loginService.setUserId(this.selectedUserId);
        } else {
            alert('Please select a user!');
        }
    }
}
