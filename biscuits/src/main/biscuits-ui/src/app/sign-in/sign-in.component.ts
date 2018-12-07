import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../authentication/services/auth.service';
import {TokenStorageService} from '../authentication/services/token-storage.service';
import {SignInInfo} from '../authentication/core/sign-in-info';
import {faUnlock, faUserSecret, faCheck} from '@fortawesome/free-solid-svg-icons';
import {Router, Routes} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  @Output() activateSuccessNotification = new EventEmitter<boolean>();
  private signInForm: FormGroup;
  private submitted: boolean = false;
  private isSignedIn: boolean = false;
  private isSignInFailed: boolean = false;
  private errorMessage: string = '';
  private roles: string[] = [];
  private signInInfo: SignInInfo;
  faUserSecret = faUserSecret;
  faUnlock = faUnlock;
  faCheck = faCheck;
  notificationIsActive: boolean = false;

  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isSignedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
    this.signInForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f() { return this.signInForm.controls; }

  onSubmit() {

    this.submitted = true;
    console.log(this.submitted);
    console.log(this.signInForm);

    this.signInInfo = new SignInInfo(
      this.signInForm.value.username,
      this.signInForm.value.password);

    this.authService.attemptAuth(this.signInInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isSignInFailed = false;
        this.isSignedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.notificationIsActive = false;
        this.activateSuccessNotification.emit(this.notificationIsActive);
        this.cancelSignIn();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignInFailed = true;
      }
    );
  }

  cancelSignIn() {
    this.router.navigateByUrl('/home');
  }

  goToSignUp() {
    this.router.navigateByUrl('/sign-up');
  }
}


