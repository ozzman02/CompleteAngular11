import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Project } from './project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  urlPrefix: string = "http://localhost:54573";


  constructor(private httpClient: HttpClient) { }

  getAllProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.urlPrefix + "/api/projects", { responseType: "json" });
  }

  insertProject(newProject: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.urlPrefix + "/api/projects", newProject, { responseType: "json" });
  }

  updateProject(existingProject: Project): Observable<Project> {
    return this.httpClient.put<Project>(this.urlPrefix + "/api/projects", existingProject, { responseType: "json" });
  }

  deleteProject(projectId: number): Observable<string> {
    return this.httpClient.delete<string>(this.urlPrefix + "/api/projects?ProjectID=" + projectId);
  }

  searchProjects(searchBy: string, searchText: string): Observable<Project[]> {
    var newSearchText = searchText === '' ? 'all' : searchText;
    return this.httpClient.get<Project[]>(
      this.urlPrefix + '/api/projects/search/' + searchBy + '/' + newSearchText, { responseType: 'json' });
  }

}
