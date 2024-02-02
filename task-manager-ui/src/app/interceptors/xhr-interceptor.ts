import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { User } from "../models/user";
import { Router } from "@angular/router";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

    user = new User();

    constructor(private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let httpHeaders = new HttpHeaders();
        if (sessionStorage.getItem('userdetails')) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
        }
        /* Important for the login operation and for other scenarios we are sending the jwt token in the else block */
        if (this.user && this.user.username && this.user.password) {
            httpHeaders = httpHeaders.append('Authorization', 'Basic ' + window.btoa(this.user.username + ':' + this.user.password));
        } else {
            let authorization = sessionStorage.getItem('Authorization');
            if (authorization) {
                httpHeaders = httpHeaders.append('Authorization', authorization);
            }
        }
        let xsrf = sessionStorage.getItem('XSRF-TOKEN');
        if (xsrf) {
            httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);  
        }
        httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
        const xhr = req.clone({
            headers: httpHeaders
        });
        return next.handle(xhr).pipe(tap(
            (error: any) => {
                if (error instanceof HttpErrorResponse) {
                    if (error.status !== 401) {
                        return;
                    }
                    this.router.navigate(['dashboard']);
                }
            }
        ));
    }
}