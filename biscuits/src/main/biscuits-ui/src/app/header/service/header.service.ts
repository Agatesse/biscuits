import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {
private _updateNavBar = new Subject<boolean>();

constructor() {

}

get updateNavBar(): Subject<boolean> {
  return this._updateNavBar;
}

toggleNavBar(value: boolean) {
this.updateNavBar.next(value);
}
}
