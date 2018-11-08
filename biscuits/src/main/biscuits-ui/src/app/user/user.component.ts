import {Component, OnInit} from '@angular/core';
import {UsersService} from "../authentication/auth/services/users.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  board: string;
  errorMessage: string;

  constructor(private usersService: UsersService) {
  }

  ngOnInit() {
    this.usersService.getUserBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

}
