import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {jwtDecode} from "jwt-decode";
import {JwtService} from "@core/auth/services/jwt.service";
import {Endpoints} from "@app/endpoints";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  public register(username: string, password: string): Observable<any> {
    return this.http.post<{ token: string }>(Endpoints.REGISTER, {username, password}).pipe(
      tap(response => this.jwtService.saveToken(response.token)),
      map(() => void 0)
    );
  }

  public login(username: string, password: string): Observable<void> {
    return this.http.post<{ token: string }>(Endpoints.LOGIN, {username, password}).pipe(
      tap(response => this.jwtService.saveToken(response.token)),
      map(() => void 0)
    );
  }

  public logout(): void {
    this.jwtService.destroyToken();
  }

  public isLoggedIn(): boolean {
    return this.jwtService.isTokenPresent();
  }

  public isAdmin(): boolean {
    const token = this.jwtService.getToken();
    if (!token) {
      return false;
    }
    const decoded: any = jwtDecode(token);
    return decoded.roles?.includes('ROLE_ADMIN');
  }

  public getAccountLabel(): string | null {
    if (!this.jwtService.isTokenPresent()) {
      return null;
    }
    const token = this.jwtService.getToken();
    const decoded: any = jwtDecode(token);
    return decoded.username ?? decoded.sub ?? decoded.email ?? null;
  }
}
