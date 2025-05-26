import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Users} from "../model/Users";
import {Endpoints} from "../../endpoints";

@Injectable({
    providedIn: 'root',
})
export class LoginService {

    private readonly SESSION_KEY = 'userId';

    constructor(private http: HttpClient) {}

    getUsers(): Observable<Users> {
        return this.http.get<Users>(Endpoints.USERS);
    }

    setUserId(userId: string): void {
        sessionStorage.setItem(this.SESSION_KEY, userId);
    }

    getUserId(): string | null {
        return sessionStorage.getItem(this.SESSION_KEY);
    }

    clearUserId(): void {
        sessionStorage.removeItem(this.SESSION_KEY);
    }

}
