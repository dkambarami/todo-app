import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { API_URL } from 'src/app/app.constants';

export class HelloWorldBean{
  constructor(public message : string ){}
}



@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  welcomeMessageFromService : string;

  constructor(private http: HttpClient) { }

  executeHelloWorldBeanService(){
    return this.http.get<HelloWorldBean>(`${API_URL}helloBean`);

  }

    executeHelloWorldBeanServiceWithPath(name){
    return this.http.get<HelloWorldBean>(`${API_URL}helloBean/${name}`);

  }

  createBasicAuthenticationHttpHeader(){
    let username = 'user';
    let password = 'user';
    let basicAuthHeaderString =
     'Basic ' +window.btoa(username +':'+password);
     return basicAuthHeaderString;
  }

}
