import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { LoginViewModel } from './login-view-model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserName: any = null;

  constructor(private httpClient: HttpClient) { }

  public Login(loginViewModel: LoginViewModel): Observable<any> {
    return this.httpClient.post<any>("/authenticate", loginViewModel, { responseType: "json" })
      .pipe(map(user => {
        if (user) {
          this.currentUserName = user.UserName;
        }
        return user;
      }));
  }

  public Logout() {
    this.currentUserName = null;
  }

}
