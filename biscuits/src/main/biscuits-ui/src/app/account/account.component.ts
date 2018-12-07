import { Component, OnInit } from '@angular/core';
import {faUserEdit, faCheck, faEnvelope, faUnlock, faUserSecret} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SignUpInfo} from "../authentication/core/sign-up-info";
import {AuthService} from "../authentication/services/auth.service";

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
  faUnlock = faUnlock;
  updateAccountForm: FormGroup;
  private submitted: boolean = false;
  private signUpInfo: SignUpInfo;
  private isSignedUp: boolean = false;
  private isSignUpFailed: boolean = false;
  private errorMessage: string = '';

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
    this.updateAccountForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() { return this.updateAccountForm.controls; }

  onSubmit() {

    this.submitted = true;
    console.log(this.submitted);

    // stop here if form is invalid
    if (this.updateAccountForm.invalid) {
      return;
    }

    if (this.updateAccountForm.value.password !== this.updateAccountForm.value.confirmPassword) {
      return;
    }

    console.log(this.updateAccountForm);

    this.signUpInfo = new SignUpInfo(
      this.updateAccountForm.value.username,
      this.updateAccountForm.value.email,
      this.updateAccountForm.value.password,
      this.updateAccountForm.value.confirmPassword
    );

    this.authService.signUp(this.signUpInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
