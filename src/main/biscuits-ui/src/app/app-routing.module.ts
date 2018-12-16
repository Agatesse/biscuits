import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MissionsComponent} from './missions/missions.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {UserComponent} from './user/user.component';
import {HomeComponent} from './home/home.component';
import {KidsComponent} from './kids/kids.component';
import { AuthGuard } from './authentication/services/auth-guards.service';


const routes: Routes = [
  {
    path: 'app/home',
    component: HomeComponent,
  },
  {
    path: 'app/account',
    component: UserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'app/sign-in',
    component: SignInComponent
  },
  {
    path: 'app/sign-up',
    component: SignUpComponent
  },
  {
    path: 'app/missions',
    component: MissionsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'app/kids',
    component: KidsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '',
    redirectTo: 'app/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
