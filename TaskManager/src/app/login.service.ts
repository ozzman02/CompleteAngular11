import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { LoginViewModel } from './login-view-model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private httpClient: HttpClient | null = null;

  currentUserName: any = null;

  /* HttpBacked represents the client without interceptors */
  constructor(private httpBackend: HttpBackend) { }

  /* We don't want the interceptor to be executed for the login */
  public Login(loginViewModel: LoginViewModel): Observable<any> {
    this.httpClient = new HttpClient(this.httpBackend);
    return this.httpClient.post<any>("/authenticate", loginViewModel, { responseType: "json" })
      .pipe(map(user => {
        if (user) {
          this.currentUserName = user.userName;
          sessionStorage['currentUser'] = JSON.stringify(user);
        }
        return user;
      }));
  }

  public Logout() {
    sessionStorage.removeItem("currentUser");
    this.currentUserName = null;
  }

}
