import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service' ;
import { ConfirmationDialogService } from './confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

      USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser' ;
      IS_ADMIN = 'authorization' ;
      constructor(
              private router: Router,
              private authenticationService: AuthenticationService,
              private confirmationDialogService: ConfirmationDialogService
          ){}

      isUserLoggedIn(){
           let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
           if (user === '#' || user===null) return false
           return true
      }

      isUserAdmin(){
            let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
            if(user ==='dugc_che'|| user ==='dmishra' || user==='shubgupt' || user==='tusharm') return true
            else return false
      }

       confirmLogout():void {
                 this.confirmationDialogService.confirm('Please Confirm..', 'Are You Sure you want to logout ?')
                      .then((confirmed) => {
                      if(confirmed)this.logout() ;
                      }).catch(() => {});
              };
      logout() {
              this.authenticationService.logout();
              this.router.navigate(['/login']);
          }
}
