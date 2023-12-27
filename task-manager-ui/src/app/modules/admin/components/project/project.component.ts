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

  editIndex: any = null;

  deleteProject: Project = new Project();

  deleteIndex: any = null;

  searchBy: string = "projectName";
  
  searchText: string = '';

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
        let p: Project = this.cloneProject(response);
        this.projects.push(p);
        this.clearProjectValues(this.newProject);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onEditClick(event: any, index: number): void {
    this.editIndex = index;
    this.editProject = this.cloneProject(this.projects[index]);
  }

  onUpdateClick(): void {
    this.projectService.updateProject(this.editProject).subscribe(
      (response: Project) => {
        let p: Project = this.cloneProject(response);
        this.projects[this.editIndex] = p;
        this.clearProjectValues(this.editProject);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onDeleteClick(event: any, index: number): void {
    this.deleteIndex = index;
    this.deleteProject = this.cloneProject(this.projects[index]);
  }

  onDeleteConfirmClick(): void {
    this.projectService.deleteProject(this.deleteProject.id).subscribe(
      (response: string) => {
        this.projects.splice(this.deleteIndex, 1);
        this.clearProjectValues(this.deleteProject);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  onSearchClick(): void {
    this.projectService.searchProjects(this.searchBy, this.searchText).subscribe(
      (response: Project[]) => {
        this.projects = response;
        
      },
      (error) => {
        console.log(error);
      }
    )
  }

  private clearProjectValues(project: Project) {
    project.id = '';
    project.projectName = '';
    project.dateOfStart = null;
    project.teamSize = 0;
    project.version = 0;
    project.createdDate = null;
    project.updateDate = null;
  }

  private cloneProject(project: Project) {
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
