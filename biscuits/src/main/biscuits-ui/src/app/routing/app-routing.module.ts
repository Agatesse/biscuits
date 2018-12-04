import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "../layout/home/home.component";
import {UserComponent} from "../user/user.component";
import {AdminComponent} from "../admin/admin.component";
import {SignInComponent} from "../authentication/sign-in/sign-in.component";
import {SignUpComponent} from "../authentication/sign-up/sign-up.component";
import {MissionsListComponent} from "../missions/missions-list/missions-list.component";


const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'auth/signin',
    component: SignInComponent
  },
  {
    path: 'signup',
    component: SignUpComponent
  },
  {
    path: 'missions',
    component: MissionsListComponent
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
export class AppRoutingModule {
}
