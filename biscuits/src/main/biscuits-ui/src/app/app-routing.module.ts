import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MissionsComponent} from './missions/missions.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AccountComponent} from './account/account.component';
import {HomeComponent} from './home/home.component';
import {KidsComponent} from './kids/kids.component';
import {BiscuitsBoxComponent} from './biscuits-box/biscuits-box.component';
import {TreasuresComponent} from './treasures/treasures.component';
import {AddMissionComponent} from './missions/add-mission/add-mission.component';
import {EditMissionComponent} from './missions/edit-mission/edit-mission.component';


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
    path: 'dashboard',
    component: DashboardComponent
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
    path: 'add-mission',
    component: AddMissionComponent
  },
  {
    path: 'edit-mission',
    component: EditMissionComponent
  },
  {
    path: 'kids',
    component: KidsComponent
  },
  {
    path: 'biscuits-box',
    component: BiscuitsBoxComponent
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
