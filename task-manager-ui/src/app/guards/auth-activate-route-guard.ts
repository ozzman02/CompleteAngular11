import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { User } from "../models/user";

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    
    user = new User();

    constructor(private router: Router) {}
    
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | 
        Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        console.log("CanActivateGuard");
        if (sessionStorage.getItem('userdetails')) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
        }
        if (!this.user) {
            this.router.navigate(['login']);
        }
        return this.user ? true : false;
    }
}
