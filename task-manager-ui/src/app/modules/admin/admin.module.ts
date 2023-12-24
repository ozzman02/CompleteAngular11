import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { AboutComponent } from './components/about/about.component';
import { DashboardService } from './services/dashboard/dashboard.service';



@NgModule({
  declarations: [
    DashboardComponent,
    MyProfileComponent,
    AboutComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DashboardComponent,
    MyProfileComponent,
    AboutComponent
  ],
  providers: [
    DashboardService
  ]
})
export class AdminModule { }
