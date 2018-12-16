import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/User';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _accountUrl = 'http://localhost:8080/api/account/';
  private _byIdUrl = this._accountUrl + 'findbyid/';
  private _updateUrl = this._accountUrl + 'update/';

  constructor(private http: HttpClient) {
  }

  findUserByUsername(username: string): Observable<User> {
    const getUserUrl = this._accountUrl + username;
    return this.http.get<User>(getUserUrl) as Observable<User>;
  }

  getUserById(id: number): Observable<User> {
    const getUserUrl = this._byIdUrl + id;
    return this.http.get<User>(getUserUrl) as Observable<User>;
  }

  updateUser(user: User): Observable<User> {
    const updateUserUrl = this._updateUrl + user.id;
    return this.http.put<User>(updateUserUrl, user, httpOptions);
  }
}
