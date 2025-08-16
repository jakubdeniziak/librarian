import {inject} from "@angular/core";
import {HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Observable} from "rxjs";
import {JwtService} from "../auth/services/jwt.service";

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> => {
  const jwtService = inject(JwtService);
  if (!jwtService.isTokenPresent()) {
    return next(req);
  }
  const requestWithBearer = req.clone({
    setHeaders: {Authorization: `Bearer ${jwtService.getToken()}`}
  });
  return next(requestWithBearer);
};
