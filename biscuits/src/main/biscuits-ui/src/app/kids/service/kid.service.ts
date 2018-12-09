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

  getKids(): Observable<Kid[]> {
    let getKids = this.kidUrl + '/all';
    return this.http.get<Kid[]>(getKids) as Observable<Kid[]>;
  }

}
