import { Component, OnInit } from '@angular/core';
import { Project } from '../../../../models/project';
import { ProjectsService } from '../../../../services/projects.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrl: './project.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class ProjectComponent implements OnInit {

  projects: Project[];

  newProject: Project = new Project();

  editProject: Project = new Project();

  editIndex: any  = null;

  constructor(private projectService: ProjectsService) { }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe(
      (response: Project[]) => {
        this.projects = response;
      }
    );
  }
  
  onSaveClick(): void {
    this.projectService.insertProject(this.newProject).subscribe(
      (response: Project) => {
        let p: Project = this.copyProject(response);
        this.projects.push(p);
        this.clearProjectValues(this.newProject);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onEditClick(event: any, index: number): void {
    /*this.editProject.id = this.projects[index].id;
    this.editProject.projectName = this.projects[index].projectName;
    this.editProject.teamSize = this.projects[index].teamSize;
    this.editProject.dateOfStart = this.projects[index].dateOfStart;
    this.editProject.createdDate = this.projects[index].createdDate;
    this.editProject.updateDate = this.projects[index].updateDate;
    this.editProject.version = this.projects[index].version;*/
    this.editProject = this.copyProject(this.projects[index]);
    this.editIndex = index;
  }

  onUpdateClick(): void {
    this.projectService.updateProject(this.editProject).subscribe(
      (response: Project) => {
        let p: Project = this.copyProject(response);
        this.projects[this.editIndex] = p;
        this.clearProjectValues(this.editProject);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  private clearProjectValues(project: Project) {
    project.id = null;
    project.projectName = null;
    project.dateOfStart = null;
    project.teamSize = null;
    project.version = null;
    project.createdDate = null;
    project.updateDate = null;
  }

  private copyProject(project: Project) {
    let p: Project = new Project();
    p.id = project.id;
    p.projectName = project.projectName;
    p.teamSize = project.teamSize;
    p.dateOfStart = project.dateOfStart;
    p.createdDate = project.createdDate;
    p.updateDate = project.updateDate;
    p.version = project.version;
    return p;
  }

}
