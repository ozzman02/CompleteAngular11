import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactDetailsComponent } from './contact-details/contact-details.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { SkillsComponent } from './skills/skills.component';
import { WizardFinishedComponent } from './wizard-finished/wizard-finished.component';
import { WorkExperienceComponent } from './work-experience/work-experience.component';

const routes: Routes = [
  { path: "", redirectTo: "personaldetails", pathMatch: "full" },
  { path: "personaldetails", component: PersonalDetailsComponent },
  { path: "contactdetails", component: ContactDetailsComponent },
  { path: "skills", component: SkillsComponent },
  { path: "workexperience", component: WorkExperienceComponent },
  { path: "wizardfinished", component: WizardFinishedComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
