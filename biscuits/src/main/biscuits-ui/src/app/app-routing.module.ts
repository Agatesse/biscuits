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
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'account',
    component: UserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'sign-in',
    component: SignInComponent
  },
  {
    path: 'sign-up',
    component: SignUpComponent
  },
  {
    path: 'missions',
    component: MissionsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'kids',
    component: KidsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
