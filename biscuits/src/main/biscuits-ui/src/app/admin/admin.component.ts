import { Component, OnInit } from '@angular/core';
import {UsersService} from "../authentication/auth/services/users.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  board: string;
  errorMessage: string;

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.usersService.getAdminBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }
}


