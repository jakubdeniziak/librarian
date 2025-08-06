import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {Endpoints} from "../../endpoints";
import {jwtDecode} from "jwt-decode";

@Injectable({
    providedIn: 'root',
})
export class UserService {

    private readonly TOKEN_KEY = 'auth_token';

    constructor(private http: HttpClient) {}

    register(username: string, password: string): Observable<any> {
        return this.http.post<{ token: string }>(Endpoints.REGISTER, { username, password }).pipe(
            tap(response => sessionStorage.setItem(this.TOKEN_KEY, response.token)),
            map(() => void 0)
        );
    }

    login(username: string, password: string): Observable<void> {
        return this.http.post<{ token: string }>(Endpoints.LOGIN, { username, password }).pipe(
            tap(response => sessionStorage.setItem(this.TOKEN_KEY, response.token)),
            map(() => void 0)
        );
    }

    logout(): void {
        sessionStorage.removeItem(this.TOKEN_KEY);
    }

    isLoggedIn(): boolean {
        return !!sessionStorage.getItem(this.TOKEN_KEY);
    }

    isAdmin(): boolean {
        const token = sessionStorage.getItem(this.TOKEN_KEY);
        if (!token) {
            return false;
        }
        const decoded: any = jwtDecode(token);
        return decoded.roles?.includes('ROLE_ADMIN');
    }

}
