import {inject} from '@angular/core';
import {HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest,} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtService} from "../core/auth/services/jwt.service";

export const AuthInterceptorFn: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
  const jwtService = inject(JwtService);
  if (!jwtService.isTokenPresent()) {
    return next(req);
  }
  return next(req.clone({
    setHeaders: {Authorization: `Bearer ${jwtService.getToken()}`},
  }));
};
