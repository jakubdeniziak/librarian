import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {jwtDecode} from "jwt-decode";

export const adminGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);
    const token = sessionStorage.getItem('auth_token');

    if (!token) {
        router.navigate(['/unauthorized']);
        return false;
    }

    const decoded: any = jwtDecode(token);
    if (decoded.roles?.includes('ROLE_ADMIN')) {
        return true;
    }

    router.navigate(['/unauthorized']);
    return false;
};
