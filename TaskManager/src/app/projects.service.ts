import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map, Observable } from 'rxjs';
import { Project } from './project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  urlPrefix: string = "http://localhost:54573";


  constructor(private httpClient: HttpClient) { }

  /* The Map is an RXJS operator which executes a function after receiving response from the server */
  getAllProjects(): Observable<Project[]> {
    var currentUser = { token: "" };
    var headers = new HttpHeaders();
    if (sessionStorage['currentUser'] != null) {
      currentUser = JSON.parse(sessionStorage['currentUser']);
      headers = headers.set("Authorization", "Bearer " + currentUser.token);
    }
    return this.httpClient.get<Project[]>(this.urlPrefix + "/api/projects", { headers: headers, responseType: "json" })
      .pipe(map(
        (data: Project[]) => {
          for (let i = 0; i < data.length; i++) {
            data[i].teamSize = data[i].teamSize * 100;
          }
          return data;
        }
      ));
  }

  insertProject(newProject: Project): Observable<Project> {
    var currentUser = { token: "" };
    var headers = new HttpHeaders();
    if (sessionStorage['currentUser'] != null) {
      currentUser = JSON.parse(sessionStorage['currentUser']);
      headers = headers.set("Authorization", "Bearer " + currentUser.token);
    }
    return this.httpClient.post<Project>(this.urlPrefix + "/api/projects", newProject, { headers: headers, responseType: "json" });
  }

  updateProject(existingProject: Project): Observable<Project> {
    var currentUser = { token: "" };
    var headers = new HttpHeaders();
    if (sessionStorage['currentUser'] != null) {
      currentUser = JSON.parse(sessionStorage['currentUser']);
      headers = headers.set("Authorization", "Bearer " + currentUser.token);
    }
    return this.httpClient.put<Project>(this.urlPrefix + "/api/projects", existingProject, { headers: headers, responseType: "json" });
  }

  deleteProject(projectId: number): Observable<string> {
    var currentUser = { token: "" };
    var headers = new HttpHeaders();
    if (sessionStorage['currentUser'] != null) {
      currentUser = JSON.parse(sessionStorage['currentUser']);
      headers = headers.set("Authorization", "Bearer " + currentUser.token);
    }
    return this.httpClient.delete<string>(this.urlPrefix + "/api/projects?ProjectID=" + projectId, { headers: headers });
  }

  searchProjects(searchBy: string, searchText: string): Observable<Project[]> {
    var currentUser = { token: "" };
    var headers = new HttpHeaders();
    if (sessionStorage['currentUser'] != null) {
      currentUser = JSON.parse(sessionStorage['currentUser']);
      headers = headers.set("Authorization", "Bearer " + currentUser.token);
    }
    var newSearchText = searchText === '' ? 'all' : searchText;
    return this.httpClient.get<Project[]>(
      this.urlPrefix + '/api/projects/search/' + searchBy + '/' + newSearchText, { headers: headers, responseType: 'json' });
  }

}
