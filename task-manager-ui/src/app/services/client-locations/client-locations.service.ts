import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientLocation } from '../../models/client-location';
import { AppConstants } from '../../constants/app.constants';

@Injectable({
  providedIn: 'root'
})
export class ClientLocationsService {

  constructor(private httpClient: HttpClient) { }

  getAllClientLocations(): Observable<ClientLocation[]> {
    return this.httpClient.get<ClientLocation[]>(AppConstants.CLIENT_LOCATIONS_API_URL, {responseType: "json"});
  }
}
