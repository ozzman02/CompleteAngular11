import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { AboutComponent } from './components/about/about.component';
import { DashboardService } from './services/dashboard/dashboard.service';
import { ProjectComponent } from './components/project/project.component';



@NgModule({
  declarations: [
    DashboardComponent,
    MyProfileComponent,
    AboutComponent,
    ProjectComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DashboardComponent,
    MyProfileComponent,
    AboutComponent,
    ProjectComponent
  ],
  providers: [
    DashboardService
  ]
})
export class AdminModule { }
