import {Component, OnInit} from '@angular/core';
import {faCookieBite, faUserPlus, faSignInAlt, faSignOutAlt} from '@fortawesome/free-solid-svg-icons';
import {Router} from "@angular/router";
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  faCookieBite = faCookieBite;
  faUserPlus = faUserPlus;
  faSignInAlt = faSignInAlt;
  faSignOutAlt = faSignOutAlt;
  toggled: boolean = false;
  private authority: string;
  private roles: string[];

  constructor(private tokenStorage: TokenStorageService, private router: Router) {
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

  toggleBurger() {
    this.toggled = this.toggled !== true;
  }

  signOut() {
    this.tokenStorage.signOut();
  /*  this.router.navigateByUrl('/home')*/
  }
}
