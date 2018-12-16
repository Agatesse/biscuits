import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../authentication/services/auth.service';
import {TokenStorageService} from '../authentication/services/token-storage.service';
import {SignInInfo} from '../authentication/core/sign-in-info';
import {faCheck, faUnlock, faUserSecret} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {HeaderService} from '../header/service/header.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

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

  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService, private headerService: HeaderService) {
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

  get f() {
    return this.signInForm.controls;
  }

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
        this.tokenStorage.saveUser(data.id);
        this.isSignInFailed = false;
        this.isSignedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.headerService.toggleNavBar(this.isSignedIn);
        this.goToKids();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignInFailed = true;
      }
    );
  }

  cancelSignIn() {
    this.router.navigate(['/home']);
  }

  goToKids() {
    this.router.navigate(['/kids']);
  }

  goToSignUp() {
    this.router.navigate(['/sign-up']);
  }
}


