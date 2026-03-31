import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {UserService} from '@core/auth/services/user.service';
import * as PAGES from '../../../pages';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const userService = inject(UserService);

  if (!userService.isLoggedIn()) {
    return router.parseUrl(PAGES.UNAUTHORIZED);
  }

  return true;
};
