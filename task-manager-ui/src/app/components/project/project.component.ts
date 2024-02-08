import { Component, OnInit, ViewChild } from '@angular/core';
import { Project } from '../../models/project';
import { ProjectsService } from '../../services/project/projects.service';
import { ClientLocation } from '../../models/client-location';
import { ClientLocationsService } from '../../services/client-locations/client-locations.service';
import { NgForm } from '@angular/forms';
import $ from "jquery";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrl: './project.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class ProjectComponent implements OnInit {

  projects: Project[] = [];

  clientLocations: ClientLocation[] = [];

  showLoading: boolean = true;

  newProject: Project = new Project();

  editProject: Project = new Project();

  editIndex: any = null;

  deleteProject: Project = new Project();

  deleteIndex: any = null;

  searchBy: string = "projectName";
  
  searchText: string = '';

  @ViewChild("newForm") newForm: NgForm | any = null;

  @ViewChild("editForm") editForm: NgForm | any = null;

  constructor(private projectService: ProjectsService, private clientLocationService: ClientLocationsService) { }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe(
      (response: any) => {
        this.projects = response.body;
        this.showLoading = false;
      },
      (error) => {
        console.log(error)
      }
    );
    this.clientLocationService.getAllClientLocations().subscribe(
      (response: any) => {
        this.clientLocations = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  isSearchByAll() {
    return this.searchBy === 'all';
  }

  onNewClick(event: any) {
      this.newForm.resetForm();
  }
  
  onSaveClick(): void {
    if (this.newForm.valid) {
      this.projectService.insertProject(this.newProject).subscribe(
        (response: any) => {
          let p: Project = this.cloneProject(response.body);
          this.projects.push(p);
          this.clearProjectValues(this.newProject);
          $("#newFormCancel").trigger("click");
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  onEditClick(event: any, index: number): void {
    this.editForm.resetForm();
    setTimeout(() => {
      this.editIndex = index;
      this.editProject = this.cloneProject(this.projects[index]);
    }, 100);
  }

  onUpdateClick(): void {
    if (this.editForm.valid) {
      this.projectService.updateProject(this.editProject).subscribe(
        (response: any) => {
          let p: Project = this.cloneProject(response.body);
          this.projects[this.editIndex] = p;
          this.clearProjectValues(this.editProject);
          $("#editFormCancel").trigger("click");
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  onDeleteClick(event: any, index: number): void {
    this.deleteIndex = index;
    this.deleteProject = this.cloneProject(this.projects[index]);
  }

  onDeleteConfirmClick(): void {
    this.projectService.deleteProject(this.deleteProject.id).subscribe(
      (response: any) => {
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
      (response: any) => {
        console.log(response);
        this.projects = response.body as Project[];
        if (this.searchBy === 'all') {
          this.searchText = '';
        }
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
    project.active = null;
    project.status = null;
    project.clientLocationId = '';
    project.clientLocation = null;
    project.version = 0;
    project.createdDate = null;
    project.updateDate = null;
  }

  private cloneProject(project: Project) {
    let p: Project = new Project();
    p.id = project.id;
    p.projectName = project.projectName;
    p.teamSize = project.teamSize;
    p.clientLocationId = project.clientLocationId;
    p.clientLocation = project.clientLocation;
    p.active = project.active;
    p.status = project.status;
    p.dateOfStart = project.dateOfStart;
    p.createdDate = project.createdDate;
    p.updateDate = project.updateDate;
    p.version = project.version;
    return p;
  }

}
