import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {Endpoints} from "../../endpoints";

@Injectable({
    providedIn: 'root',
})
export class LoginService {

    private readonly TOKEN_KEY = 'auth_token';

    constructor(private http: HttpClient) {}

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

}
