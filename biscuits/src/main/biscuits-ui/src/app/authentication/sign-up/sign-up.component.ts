import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SignUpInfo} from "../auth/core/signup-info";
import {AuthService} from "../auth/services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  @Output() messageEvent = new EventEmitter<boolean>();
  signUpForm: FormGroup;
  submitted = false;
  toSignIn: boolean = false;
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      familyName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() { return this.signUpForm.controls; }

  onSubmit() {

    this.submitted = true;
    console.log(this.submitted);

    // stop here if form is invalid
    if (this.signUpForm.invalid) {
      return;
    }

    if (this.signUpForm.value.password !== this.signUpForm.value.confirmPassword) {
      return;
    }

    console.log(this.signUpForm);

    this.signupInfo = new SignUpInfo(
      this.signUpForm.value.familyName,
      this.signUpForm.value.email,
      this.signUpForm.value.password
    );

    this.authService.signUp(this.signupInfo).subscribe(
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

  goToSignIn() {
    this.toSignIn = !this.toSignIn;
    this.messageEvent.emit(this.toSignIn)
  }

}
