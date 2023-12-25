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

  constructor(private projectService: ProjectsService) { }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe(
      (response: Project[]) => {
        this.projects = response;
      }
    );
  }
  

}
