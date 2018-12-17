import {Component, OnInit} from '@angular/core';
import {faCheck, faEnvelope, faUserEdit, faUserSecret, faArrowLeft} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from './service/user.service';
import {User} from './model/User';
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  faUserEdit = faUserEdit;
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  faEnvelope = faEnvelope;
  faArrowLeft = faArrowLeft;
  user: User;
  updateUserForm: FormGroup;
  allowEdit: boolean = false;
  submitted: boolean = false;
  isUpdated: boolean = false;
  isNotUpdated: boolean = false;

  constructor(private router: Router, private formBuilder: FormBuilder, private userService: UserService, private tokenStorageService: TokenStorageService) {
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

  goToKids() {
    this.router.navigate(['/app/kids']);
  }

  get f() {
    return this.updateUserForm.controls;
  }

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
