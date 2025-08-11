import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {jwtDecode} from "jwt-decode";
import {JwtService} from "../core/auth/services/jwt.service";

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const jwtService = inject(JwtService);

  if (!jwtService.isTokenPresent()) {
    router.navigate(['/unauthorized']);
    return false;
  }

  const decoded: any = jwtDecode(jwtService.getToken());
  if (decoded.roles?.includes('ROLE_ADMIN')) {
    return true;
  }

  router.navigate(['/unauthorized']);
  return false;
};
