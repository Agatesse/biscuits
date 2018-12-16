import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  username: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
        if (this.tokenStorageService.getToken()) {
      this.username = this.tokenStorageService.getUsername();
    }
  }
}
