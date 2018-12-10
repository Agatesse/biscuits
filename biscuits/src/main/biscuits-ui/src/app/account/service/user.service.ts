import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/User';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private accountUrl = 'http://localhost:8080/api/account';

  constructor(private http: HttpClient) { }

  findUserByUsername(username: string): Observable<User> {
    let getUserUrl = this.accountUrl + "/" + username;
    return this.http.get<User>(getUserUrl) as Observable<User>;
  }

  updateUser(user: User): Observable<User> {
  let updateUserUrl = this.accountUrl + "/update/" + user.id;
    return this.http.put<User>(updateUserUrl, user, httpOptions);
  }
}
