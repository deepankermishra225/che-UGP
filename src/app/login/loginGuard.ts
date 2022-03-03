import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

import { AuthenticationService } from '../authentication.service';

@Injectable({ providedIn: 'root' })
export class LoginGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {}

    canActivate() {
        if (this.authenticationService.isUserLoggedIn()) {
            return false;
        } else {
          return true;
        }
    }


}
