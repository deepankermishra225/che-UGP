import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders , HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import {User} from './models/User';
import {Message} from './models/Message';

const USER = {
        username: '#',
        password: '#',
} ;

const URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

    public user = USER ;

    constructor(private http: HttpClient) {

    }

    connect(user: User): Observable<string>{
       return this.http.post<string>(`${URL}/login`, user, {responseType: 'text' as 'json'});
    }

    authentication(user: User){
           this.user.username = user.username! ;
           this.user.password = user.password! ;
           this.registerSuccessfulLogin(user.username! , user.password!);
    }

    registerSuccessfulLogin(username: string , password: string): void {
      sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
    }

    logout(): void {
      sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
      this.user.username = '' ;
      this.user.password = '' ;
    }

    isUserLoggedIn(){
      let currUser = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
      if (currUser === '#'||currUser===null) return false
      return true
    }

    isUserAdmin(){
        let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
        if(user ==='dugc_che'|| user ==='dmishra'|| user==='shubgupt' || user==='tusharm') return true
        else return false
    }



    getLoggedInUserName() {
      let currUser = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
      if (currUser === null) return ''
      return currUser
    }
}
