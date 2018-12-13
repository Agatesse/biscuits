import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MissionsComponent} from './missions/missions.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {AccountComponent} from './account/account.component';
import {HomeComponent} from './home/home.component';
import {KidsComponent} from './kids/kids.component';
import {TreasuresComponent} from './treasures/treasures.component';
import { MissionsTestComponent } from './missions-test/missions-test.component';


const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'account',
    component: AccountComponent
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
    component: MissionsComponent
  },
  {
    path: 'bla',
    component: MissionsTestComponent
  },
  {
    path: 'kids',
    component: KidsComponent
  },
  {
    path: 'treasures',
    component: TreasuresComponent
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
