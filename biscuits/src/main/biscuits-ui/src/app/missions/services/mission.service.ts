import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Mission} from "../model/Mission";
import {ApiResponse} from '../../shared/models/api.response';

@Injectable({
  providedIn: 'root'
})
export class MissionService {
  private baseUrl = 'http://localhost:8080/api/missions';

  constructor(private http: HttpClient) {
  }

  getMissions() {
  let getMissions = this.baseUrl + '/all';
    return this.http.get<Mission[]>(getMissions);
  }

  getMissionById(id: number)  {
    let getMissionById = this.baseUrl + '/' + id;
    return this.http.get<Mission>(getMissionById);
  }

  createMission(mission: Mission) {
    let createMission = this.baseUrl + '/create';
    return this.http.post<Mission>(createMission, mission);
  }

 updateMission(mission: Mission) {
    let updateMission = this.baseUrl + '/update/' + mission.id;
    return this.http.put(updateMission, mission);
  }

  deleteMission(id: number) {
    let deleteMission = this.baseUrl + '/delete/' + id;
    return this.http.delete(deleteMission);
  }

  isMissionDone(id: number) {
    let isMissionDone = this.baseUrl + '/is-done/' + id;
    return this.http.patch(isMissionDone, id);
  }

}
