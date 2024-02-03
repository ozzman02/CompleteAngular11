import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { User } from "../models/user";
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    
    user = new User();

    constructor(private router: Router, private jwtHelperService: JwtHelperService) {}
    
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | 
        Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        if (sessionStorage.getItem('userdetails') && !this.jwtHelperService.isTokenExpired()) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
        }
        if (!this.user || this.jwtHelperService.isTokenExpired()) {
            this.router.navigate(['login']);
        }
        return this.user ? true : false;
    }
}
