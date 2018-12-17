import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SignUpInfo} from '../authentication/core/sign-up-info';
import {AuthService} from '../authentication/services/auth.service';
import {faCheck, faEnvelope, faUnlock, faUserSecret} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;
  submitted: boolean = false;
  signUpInfo: SignUpInfo;
  isSignedUp: boolean = false;
  isSignUpFailed: boolean = false;
  errorMessage: string = '';
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  faEnvelope = faEnvelope;
  faUnlock = faUnlock;
  notificationIsActive: boolean = false;

  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() {
    return this.signUpForm.controls;
  }

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

    this.signUpInfo = new SignUpInfo(
      this.signUpForm.value.username,
      this.signUpForm.value.email,
      this.signUpForm.value.password,
      this.signUpForm.value.confirmPassword
    );

    this.authService.signUp(this.signUpInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.notificationIsActive = true;
        this.goToSignIn();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  cancelSignUp() {
    this.router.navigateByUrl('/app/home');
  }

  goToSignIn() {
    this.router.navigateByUrl('/app/sign-in');
  }

}
