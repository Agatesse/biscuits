import {Component, OnInit} from '@angular/core';
import {faCookieBite, faUserPlus, faSignInAlt, faSignOutAlt} from '@fortawesome/free-solid-svg-icons';
import {Router} from "@angular/router";
import {TokenStorageService} from '../authentication/services/token-storage.service';
import {HeaderService} from './service/header.service'

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
  isSignedIn: boolean = false;

  constructor(private tokenStorage: TokenStorageService, private router: Router, private headerService: HeaderService){

  }

  ngOnInit() {
    this.headerService.updateNavBar.subscribe(
      data => {
        this.isSignedIn = data;
      })
  }

  toggleBurger() {
    this.toggled = this.toggled !== true;
  }

  signOut() {
    this.tokenStorage.signOut();
    this.authority = null;
    this.roles = [];
    this.headerService.toggleNavBar(false);
    this.router.navigate(['/sign-in']).then(() => {this.router.navigate(['/home'])});
  }
}
