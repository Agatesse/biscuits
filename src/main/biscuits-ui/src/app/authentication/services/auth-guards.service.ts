import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {TokenStorageService} from "./token-storage.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private tokenService: TokenStorageService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.tokenService.isAuthenticated();

  }
}
