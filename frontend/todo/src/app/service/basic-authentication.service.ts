import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private http: HttpClient) { }

  executeJWTAuthenticationService(username, password) {

 return this.http.post<any>('http://localhost:8080/authenticate', {username,password })
    .pipe(
      map(
        data =>{
          sessionStorage.setItem('authenticatedUser',username);
          sessionStorage.setItem('token','Bearer '+data.token);
          return data;
        }
      )
    );
  }


  executeBasicAuthenticationService(username, password) {

    let basicAuthHeaderString =
      'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
      Authorization: basicAuthHeaderString
    });
    return this.http.get<AuthenticationBean>('http://localhost:8080/basicauth', { headers })
    .pipe(
      map(
        data =>{
          sessionStorage.setItem('authenticatedUser',username);
          sessionStorage.setItem('token',basicAuthHeaderString);
          return data;
        }
      )
    );
  }

  getAuthenticatedUser(){
    return sessionStorage.getItem('authenticatedUser');
  }

  getAuthenticationToken(){
    return sessionStorage.getItem('token');
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticatedUser');
    return !(user === null)
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
    sessionStorage.removeItem('token');
  }


}

export class AuthenticationBean {

  constructor(public message: string) { }

}
