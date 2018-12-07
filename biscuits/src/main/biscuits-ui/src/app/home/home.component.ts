import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;
  notificationIsActive: boolean = false;
  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      userName: this.token.getUserName(),
      authorities: this.token.getAuthorities()
    };
  }

activateSuccessNotification($event) {
    this.notificationIsActive= !this.notificationIsActive;
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
