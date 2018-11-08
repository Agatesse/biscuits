import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthLoginInfo} from "../auth/core/auth-login-info";
import {AuthService} from "../auth/services/auth.service";
import {TokenStorageService} from "../auth/services/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  @Output() messageEvent = new EventEmitter<boolean>();
  signInForm: FormGroup;
  submitted = false;
  toSignUp: boolean = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
    this.signInForm = this.formBuilder.group({
      familyName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() { return this.signInForm.controls; }

  onSubmit() {
    console.log(this.signInForm);

    this.loginInfo = new AuthLoginInfo(
      this.signInForm.value.familyName,
      this.signInForm.value.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

  goToSignUp() {
    this.toSignUp = !this.toSignUp;
    this.messageEvent.emit(this.toSignUp)
  }

}
