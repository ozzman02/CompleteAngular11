import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Project } from '../../models/project';
import { AppConstants } from '../../constants/app.constants';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  constructor(private httpClient: HttpClient) { }

  getAllProjects() {
    return this.httpClient.get<Project[]>(AppConstants.PROJECTS_API_URL, {responseType: "json"}).pipe(
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

  /*getAllProjects() {
    return this.httpClient.get<Project[]>(AppConstants.PROJECTS_API_URL, { observe: 'response' });
  }*/

  insertProject(newProject: Project) {
    return this.httpClient.post<Project>(AppConstants.PROJECTS_API_URL, newProject, { observe: 'response', withCredentials: true });
  }

  updateProject(existingProject: Project) {
    return this.httpClient.put<Project>(AppConstants.PROJECTS_API_URL, existingProject, { observe: 'response', withCredentials: true });
  }

  deleteProject(projectId: string | null) {
    return this.httpClient.delete<string>(AppConstants.PROJECTS_API_URL + "?projectId=" + projectId, { observe: 'response', withCredentials: true });
  }

  searchProjects(searchBy: string, searchText: string) {
    if (typeof searchText === 'string' && searchText.trim().length === 0) {
      searchText = 'all';
    }
    return this.httpClient.get<Project[]>(AppConstants.PROJECTS_API_URL + "/search/" + searchBy + "/" + searchText, { observe: 'response', withCredentials: true });
  }

}
