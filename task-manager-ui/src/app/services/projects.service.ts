import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private urlEndpoint: string = 'http://localhost:8080/api/projects'

  constructor(private httpClient: HttpClient) { }

  getAllProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.urlEndpoint).pipe(
      map(
        (data: Project[]) => {
          for(let i = 0; i < data.length; i++) {
            data[i].teamSize = data[i].teamSize * 100;
          }
          return data;
        }
      )
    );
  }

  insertProject(newProject: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.urlEndpoint, newProject);
  }

  updateProject(existingProject: Project): Observable<Project> {
    return this.httpClient.put<Project>(this.urlEndpoint, existingProject);
  }

  deleteProject(projectId: string | null): Observable<string> {
    return this.httpClient.delete<string>(this.urlEndpoint + "?projectId=" + projectId);
  }

  searchProjects(searchBy: string, searchText: string): Observable<Project[]> {
    if (typeof searchText === 'string' && searchText.trim().length === 0) {
      searchText = 'all';
    }
    return this.httpClient.get<Project[]>(this.urlEndpoint + "/search/" + searchBy + "/" + searchText);
  }

}
