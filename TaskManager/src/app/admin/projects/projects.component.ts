import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/project';
import { ProjectsService } from 'src/app/projects.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {

  projects: Project[] = [];
  newProject: Project = new Project();
  editProject: Project = new Project();
  editIndex: any = null;
  deleteProject: Project = new Project();
  deleteIndex: any = null;
  pipe = new DatePipe('en-US'); // Use your own locale
  
  constructor(private projectsService: ProjectsService) {}

  ngOnInit(): void {
    this.projectsService.getAllProjects().subscribe(
      (response: Project[]) => {
        this.projects = response;
      }
    );
  }

  /* 
    this.projectsService.insertProject(this.newProject).subscribe(()=>{}, ()=>{}); 
    first function  () => {} is handleData
    second function () => {} is handleError
  */
  onSaveClick() {
    this.projectsService.insertProject(this.newProject).subscribe((response) => {
      //Add Project to Grid
      var p: Project = new Project();
      p.projectID = response.projectID;
      p.projectName = response.projectName;
      p.dateOfStart = response.dateOfStart;
      p.teamSize = response.teamSize;
      this.projects.push(p);

      //Clear New Project Dialog - TextBoxes
      this.newProject.projectID = null;
      this.newProject.projectName = null;
      this.newProject.dateOfStart = null;
      this.newProject.teamSize = null;
    }, (error) => {
      console.log(error);
    });
  }

  onEditClick(event: any, index: number) {
    this.editProject.projectID = this.projects[index].projectID;
    this.editProject.projectName = this.projects[index].projectName;
    this.editProject.dateOfStart  = this.pipe.transform(this.projects[index].dateOfStart, 'yyyy-MM-dd');
    this.editProject.teamSize = this.projects[index].teamSize;
    this.editIndex = index;
    console.log("Current project before saving: " + JSON.stringify(this.editProject));
  }

  onUpdateClick() {
    this.projectsService.updateProject(this.editProject).subscribe(
      (response: Project) =>
      {
        var p: Project = new Project();
        p.projectID = response.projectID;
        p.projectName = response.projectName;
        p.dateOfStart = response.dateOfStart;
        p.teamSize = response.teamSize;
        this.projects[this.editIndex] = p;

        this.editProject.projectID = null;
        this.editProject.projectName = null;
        this.editProject.dateOfStart = null;
        this.editProject.teamSize = null;
      },
      (error) =>
      {
        console.log(error);
      }
    );
  }

}
