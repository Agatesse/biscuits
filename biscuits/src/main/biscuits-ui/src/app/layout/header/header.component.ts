import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../authentication/auth/services/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private roles: string[];
  private authority: string;
  private signInIsActive: boolean;
  private signUpIsActive: boolean;
  private signInToSignUp: boolean = false;
  private signUpToSignIn: boolean = false;

  constructor(private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }
  signIn() {
    this.signInIsActive = !this.signInIsActive;
  }

  signUp() {
    this.signUpIsActive = !this.signUpIsActive;
  }

  signOut() {
    this.tokenStorage.signOut();
    window.location.reload();
  }

  receiveMessageFromSignIn($event) {
    this.signInToSignUp = $event;
    this.signIn();
    this.signUp();
  }

  receiveMessageFromSignUp($event) {
    this.signUpToSignIn = $event;
    this.signUp();
    this.signIn();
  }
}
