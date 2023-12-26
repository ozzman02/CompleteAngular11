import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private urlEndpoint: string = 'http://localhost:8080/api/projects'

  constructor(private httpClient: HttpClient) { }

  getAllProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.urlEndpoint);
  }

  insertProject(newProject: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.urlEndpoint, newProject);
  }

  updateProject(existingProject: Project): Observable<Project> {
    return this.httpClient.put<Project>(this.urlEndpoint, existingProject);
  }

}
