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
    let getMissionById = this.baseUrl + '/' + id;
    return this.http.get(getMissionById);
  }

  retrieveAllMissions(): Observable<any> {
  let getAllMissions = this.baseUrl + '/all';
    return this.http.get(getAllMissions);
  }

  addMission(mission: Mission): Observable<Object> {
    let createMission = this.baseUrl + '/create';
    return this.http.post(createMission, mission);
  }

  updateMission(id: number, value: string): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  removeMission(id: number): Observable<any> {
    let deleteMission = this.baseUrl + '/delete/' + id;
    return this.http.delete(deleteMission, { responseType: 'text' });
  }

  removeAllMissions(): Observable<any> {
    let deleteAllMissions = this.baseUrl + '/delete/';
    return this.http.delete(deleteAllMissions, { responseType: 'text' });
  }

}
