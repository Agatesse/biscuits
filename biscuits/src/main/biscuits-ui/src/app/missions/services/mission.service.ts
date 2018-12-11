import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Mission} from "../model/Mission";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MissionService {
  private _missionUrl: string = 'http://localhost:8080/api/missions';
  private _getMissionsUrl: string = this._missionUrl + '/findmissionsbykid/';
  private _createMissionUrl: string = this._missionUrl + '/create';
  private _updateMissionUrl: string = this._missionUrl + '/update/';
  private _deleteMissionUrl: string = this._missionUrl + '/delete/';
  private _isMissionDoneUrl: string = this._missionUrl + '/isdone/'

  constructor(private http: HttpClient) {
  }

  createMission(mission: Mission) {
    return this.http.post<Mission>(this._createMissionUrl, mission, httpOptions);
  }

  getMissions(kidId: number): Observable<Mission[]> {
    const getMissions = this._getMissionsUrl + kidId;
    return this.http.get<Mission[]>(getMissions);
  }

 updateMission(missionId: number, mission: Mission): Observable<Mission> {
    const updateMission = this._updateMissionUrl + missionId;
    return this.http.put<Mission>(updateMission, mission, httpOptions);
  }

  deleteMission(missionId: number) {
    const deleteMission = this._deleteMissionUrl + missionId;
    return this.http.delete(deleteMission);
  }

  isMissionDone(missionId: number): Observable<Mission> {
    const isMissionDone = this._isMissionDoneUrl + missionId;
    return this.http.patch<Mission>(isMissionDone, missionId, httpOptions);
  }

}
