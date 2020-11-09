import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  errorMessage = 'Invalid Login';
  invalidLogin = true;

  constructor(private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService,
    private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit(): void {
  }

  handleLogin() {
    console.log('Login Successful for ' + this.username);
    console.log('Password is ' + this.password);
    if (this.hardcodedAuthenticationService.authenticate(this.username, this.password)) {
      this.router.navigate(['welcome', this.username]); //pass parameter by routing
      this.invalidLogin = false;
    }
    else {
      this.invalidLogin = true;
    }
  }

  handleBasicAuthLogin() {
    this.basicAuthenticationService.executeBasicAuthenticationService(this.username, this.password)
      .subscribe(
        data => {
          console.log('successful login '+data);
          this.invalidLogin = false;
          this.router.navigate(['welcome', this.username]); //pass parameter by routing
          
        },
        error => {
          this.invalidLogin = true;
          console.log('failed login '+error);
          this.router.navigate(['login']);
          console.log(error);
        }

      )
  }

  handleJWTAuthLogin() {
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password)
      .subscribe(
        data => {
          console.log(this.username+'successful login here in handle jwt '+data.token);
          this.invalidLogin = false;
          sessionStorage.setItem('username',this.username);
          console.log('routing to welcome page');
          this.router.navigate(['welcome', this.username]); //pass parameter by routing
          
        },
        error => {
          this.invalidLogin = true;
          console.log('failed login '+error);
          this.router.navigate(['login']);
          console.log(error);
        }

      )
  }

}
