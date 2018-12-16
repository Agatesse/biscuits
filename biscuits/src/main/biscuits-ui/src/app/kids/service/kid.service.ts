import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../user/model/User';
import {Kid} from '../model/Kid';
import {Subject, BehaviorSubject} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class KidService {
  private _kidUrl = 'http://localhost:8080/api/kids';
  private _createKidUrl: string = this._kidUrl + '/create';
  private _getKidsUrl: string = this._kidUrl + '/findkidsbyuser/';
  private _getKid: string = this._kidUrl + '/findkidsbynickname/';
  private _updateKidUrl: string = this._kidUrl + '/update/';
  private _deleteKidUrl: string = this._kidUrl + '/delete/';
  private _selectedKid: Subject<Kid> = new BehaviorSubject<Kid>(null);

  constructor(private http: HttpClient) { }

  createKid(kid: Kid) {
    console.log(kid);
    return this.http.post<User>(this._createKidUrl, kid, httpOptions);
  }

  getKids(userId: number): Observable<Kid[]> {
    const getKids = this._getKidsUrl + userId;
    return this.http.get<Kid[]>(getKids) as Observable<Kid[]>;
  }

  getKid(kidNickname: string): Observable<Kid> {
    const getKid = this._getKid + kidNickname;
    return this.http.get<Kid>(getKid) as Observable<Kid>;
  }

   updateKid(kidId: number, kid: Kid): Observable<Kid> {
  const updateKid: string = this._updateKidUrl + kidId;
    return this.http.put<Kid>(updateKid, kid, httpOptions);
  }

  deleteKid(kidId: number) {
    const deleteKid = this._deleteKidUrl + kidId;
    return this.http.delete(deleteKid);
  }

  get updateSelectedKid(): Subject<Kid> {
    return this._selectedKid;
  }

  toggleIsSelected(value: Kid) {
    this.updateSelectedKid.next(value);
  }
}
