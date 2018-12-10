import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../account/model/User';
import {Kid} from '../model/Kid';
import {Mission} from '../../missions/model/Mission';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class KidService {
  private kidUrl = 'http://localhost:8080/api/kids';

  constructor(private http: HttpClient) { }

  createKid(kid: Kid){
    let createKid = this.kidUrl + '/create';
    return this.http.post<User>(createKid, kid, httpOptions);
  }

  getKids(userId: number): Observable<Kid[]> {
    let getKids = this.kidUrl + '/findkidsbyuser/' + userId;
    return this.http.get<Kid[]>(getKids) as Observable<Kid[]>;
  }

   updateKid(kidId: number, kid: Kid): Observable<Kid> {
  let updateKidUrl = this.kidUrl + "/update/" + kidId;
    return this.http.put<Kid>(updateKidUrl, Kid, httpOptions);
  }

  deleteKid(kidId: number) {
    let deleteKidUrl = this.kidUrl + "/delete/" + kidId;
    return this.http.delete(deleteKidUrl);
  }

}
