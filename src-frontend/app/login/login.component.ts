import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import {User} from '../models/User';
import {Message} from '../models/Message';

const USER = {
        username: '',
        password: '',
} ;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit  {

  user = USER ;
  errorMessage = 'Invalid Credentials';
  successMessage?: string;
  invalidLogin = false;
  loginSuccess = false;
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'



  ngOnInit(){

  }
  isUserLoggedIn(){
        let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === '#' || user === null) return false
        return true
    }



    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService) {   }

      login(): void {
          if(this.user.username!='' && this.user.password!=''){
             this.handleLogin();
          }else {
               this.invalidLogin = true;
               this.loginSuccess = false;
          }
      }

      handleLogin(): void {
         this.authenticationService.connect(this.user).subscribe((status: string) =>{
          if(status==='admin' || status ==='true'){
             this.invalidLogin = false;
             this.loginSuccess = true;
             this.successMessage = 'Login Successful';
             this.authenticationService.authentication(this.user) ;
             if(status==='admin')this.authenticationService.adminAccess('true') ;
             else this.authenticationService.adminAccess('false') ;
             this.user.username = '' ;
             this.user.password = '' ;
             this.router.navigate(['/form']);
          }else{
              this.invalidLogin = true;
              this.loginSuccess = false;
          }
         })
      }

}
