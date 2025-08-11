import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private static readonly TOKEN_KEY: string = "auth_token";

  public saveToken(token: string): void {
    sessionStorage.setItem(JwtService.TOKEN_KEY, token);
  }

  public destroyToken(): void {
    sessionStorage.removeItem(JwtService.TOKEN_KEY);
  }

  public getToken(): string {
    const token = sessionStorage.getItem(JwtService.TOKEN_KEY);
    if (token === null) {
      throw new Error('Auth token is missing from session storage.');
    }
    return token;
  }

  public isTokenPresent(): boolean {
    return !!sessionStorage.getItem(JwtService.TOKEN_KEY);
  }
}
