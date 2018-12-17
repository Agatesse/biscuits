import {Component, OnInit} from '@angular/core';
import {faCookieBite, faUserPlus, faSignInAlt, faSignOutAlt, faCogs} from '@fortawesome/free-solid-svg-icons';
import {Router} from "@angular/router";
import {TokenStorageService} from '../authentication/services/token-storage.service';
import {HeaderService} from './service/header.service'
import { TranslateService } from '@ngx-translate/core';

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
  faCogs = faCogs;
  toggled = false;
  private authority: string;
  private roles: string[];
  isSignedIn = false;

  constructor(private tokenStorage: TokenStorageService, private router: Router,
     private headerService: HeaderService, private translateService: TranslateService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isSignedIn = true;
    }
    this.headerService.updateNavBar.subscribe(
      data => {
        this.isSignedIn = data;
      });
  }

  toggleBurger() {
    this.toggled = this.toggled !== true;
  }

  translate(language: string) {
    this.translateService.use('fr');
}

  signOut() {
    this.tokenStorage.signOut();
    this.authority = null;
    this.roles = [];
    this.headerService.toggleNavBar(false);
    this.router.navigate(['/app/sign-in']).then(() => {this.router.navigate(['/app/home'])});
    console.log(this.isSignedIn);
  }
}
