import { Component, OnInit } from '@angular/core';
import {faUserEdit, faCheck, faEnvelope, faUserSecret} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from './service/user.service';
import {User} from './model/User';
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  faUserEdit = faUserEdit;
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  faEnvelope = faEnvelope;
  user: User;
  updateUserForm: FormGroup;
  private allowEdit: boolean = false;
  private submitted: boolean = false;
  private isUpdated: boolean = false;
  private isNotUpdated: boolean = false;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private tokenStorageService: TokenStorageService) {
    this.user = new User();
  }

  ngOnInit() {
    const username: string = this.tokenStorageService.getUsername();
    this.findUserByUsername(username);
    this.updateUserForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

 findUserByUsername(username: string) {
    this.userService.findUserByUsername(username).subscribe(
    data => {
      this.user = data;
    },
    error => {
      console.log(error);
    }
  );
}

  switchEdit() {
    this.allowEdit = !this.allowEdit;
  }

  get f() { return this.updateUserForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.allowEdit = false;
    if (this.updateUserForm.invalid) {
      return;
    }
    this.user.email = this.updateUserForm.controls.email.value;
    this.userService.updateUser(this.user).subscribe(
      data => {
        console.log(data);
        this.isUpdated = true;
      },
      error => {
        console.log(error);
        this.isNotUpdated = true;
      }
    );
  }
}
