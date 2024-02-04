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

        let valid: boolean = false;
        let token = JSON.stringify(this.jwtHelperService.tokenGetter());
        this.user = JSON.parse(sessionStorage.getItem('userdetails')!); 
        
        if (Object.keys(this.user).length !== 0 && !this.jwtHelperService.isTokenExpired() 
            && this.jwtHelperService.decodeToken(token).authorities.includes(route.data["expectedRole"])) {
            valid = true;
        } 
        if (Object.keys(this.user).length === 0 || this.jwtHelperService.isTokenExpired()) {
            this.router.navigate(['login']);
            valid = false;
        }
        return valid;
    }
}
