import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Mission} from "../model/Mission";

@Injectable({
  providedIn: 'root'
})
export class MissionService {

  private baseUrl = 'http://localhost:8080/api/missions';

  constructor(private http: HttpClient) {
  }
  retrieveMissionById(id: number): Observable<Object> {
    return this.http.get(this.baseUrl + id);
  }

  retrieveAllMissions(): Observable<any> {
    return this.http.get(this.baseUrl + '/all');
  }

  selectMission(mission: Mission): Observable<Object> {
    return this.http.post('${this.baseUrl}' + '/create', mission);
  }

  updateMission(id: number, value: string): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteMission(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  deleteAllMissions(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }

}
