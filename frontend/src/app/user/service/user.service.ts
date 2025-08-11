import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {Endpoints} from "../../endpoints";
import {jwtDecode} from "jwt-decode";
import {JwtService} from "../../core/auth/services/jwt.service";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post<{ token: string }>(Endpoints.REGISTER, {username, password}).pipe(
      tap(response => this.jwtService.saveToken(response.token)),
      map(() => void 0)
    );
  }

  login(username: string, password: string): Observable<void> {
    return this.http.post<{ token: string }>(Endpoints.LOGIN, {username, password}).pipe(
      tap(response => this.jwtService.saveToken(response.token)),
      map(() => void 0)
    );
  }

  logout(): void {
    this.jwtService.destroyToken();
  }

  isLoggedIn(): boolean {
    return this.jwtService.isTokenPresent();
  }

  isAdmin(): boolean {
    const token = this.jwtService.getToken();
    if (!token) {
      return false;
    }
    const decoded: any = jwtDecode(token);
    return decoded.roles?.includes('ROLE_ADMIN');
  }
}
