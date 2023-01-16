import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class CanActivateGuardService implements CanActivate{

  constructor(private loginService: LoginService, private router: Router) { }

  /*
    The CanActivate guard executes automatically before entering or navigating to the specific route,
    i.e. the location to where the user wants to jump.
  */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    console.log(this.router.url);
    if (this.loginService.isAuthenticated()) {
      return true; //the user can navigate to the particular route
    } else {
      this.router.navigate([ "login" ]);
      return false; //the user can't navigate to the particular route
    }
  }
}
