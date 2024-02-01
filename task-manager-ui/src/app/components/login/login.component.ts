import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { LoginService } from '../../services/login/login.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { getCookie } from 'typescript-cookie';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class LoginComponent implements OnInit {
  
  authStatus: string = "";

  user: User = new User();

  loginError: string = "";

  constructor(private loginService: LoginService, private router: Router) { }
  
  ngOnInit(): void { }

  /*validateUser(loginForm: NgForm): void {
    this.loginService.validateLoginDetails(this.user).subscribe(
      (response) => {
        console.log(response.body);
        //sessionStorage.setItem("Authorization", response.headers.get('Authorization')!);
        this.user = <any> response.body;
        let xsrf = getCookie('XSRF-TOKEN')!;
        sessionStorage.setItem("XSRF-TOKEN", xsrf);
        this.user.authStatus = 'AUTH';
        sessionStorage.setItem("userdetails", JSON.stringify(this.user));
        this.router.navigate(['dashboard']);
      },
      (error) => { 
        console.log(error); 
        this.loginError = "Invalid Username or Password";
      }
    )
  }*/

  onLoginClick(event: any) {
    console.log("onLoginClick");
    this.loginService.login(this.user).subscribe(
      (response) => {
        let password = this.user.password;
        this.user = <any> response.body;
        this.user.password = password;
        let xsrf = getCookie('XSRF-TOKEN')!;
        sessionStorage.setItem("XSRF-TOKEN", xsrf);
        sessionStorage.setItem("userdetails", JSON.stringify(this.user));
        //this.router.navigateByUrl("/dashboard");
        this.router.navigate(['dashboard']);
      },
      (error) => {
        console.log(error);
        this.loginError = "Invalid username or password"
      }
    );
  }

}


