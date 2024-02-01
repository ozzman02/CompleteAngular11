import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule, provideHttpClient, withFetch } from '@angular/common/http';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AboutComponent } from './components/about/about.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { ProjectComponent } from './components/project/project.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { XhrInterceptor } from './interceptors/xhr-interceptor';
import { AuthActivateRouteGuard } from './guards/auth-activate-route-guard';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AboutComponent,
    MyProfileComponent,
    ProjectComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }),
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()),
    [
      {
        provide: HTTP_INTERCEPTORS,
        useClass: XhrInterceptor,
        multi: true
      }, AuthActivateRouteGuard
    ]
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
