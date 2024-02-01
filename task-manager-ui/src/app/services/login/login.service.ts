import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../models/user';
import { AppConstants } from '../../constants/app.constants';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserName: string | null;

  constructor(private httpClient: HttpClient) { }

  /*validateLoginDetails(user: User) {
    console.log("Validating user: ");
    console.log(user);
    sessionStorage.setItem("userdetails", JSON.stringify(user)); 
    return this.httpClient.get<User>(AppConstants.USER_API_URL, { observe: 'response', withCredentials: true });
  }*/

  login(user: User): Observable<any> {
    sessionStorage.setItem("userdetails", JSON.stringify(user));
    return this.httpClient.get<User>(AppConstants.USER_API_URL, { observe: 'response', withCredentials: true, responseType: "json" })
      .pipe(map(u => {
        if (u) {
          this.currentUserName = user.username;
        }
        return u;
      }));
  }

  logout() {
    this.currentUserName = null;
  }
  
}
