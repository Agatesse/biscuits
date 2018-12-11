import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faCoffee,
  faCookieBite,
  faUserPlus,
  faSignInAlt,
  faUserSecret,
  faCheck,
  faEnvelope,
  faUnlock,
  faUserEdit,
  faEdit,
  faTimes,
  faThumbsUp,
  faThumbsDown,
  faSignOutAlt,
  faPlus
} from '@fortawesome/free-solid-svg-icons';
import { AccountComponent } from './account/account.component';
import { MissionsComponent } from './missions/missions.component';
import { BiscuitsBoxComponent } from './biscuits-box/biscuits-box.component';
import { TreasuresComponent } from './treasures/treasures.component';
import { KidsComponent } from './kids/kids.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { MissionDetailsComponent } from './missions/mission-details/mission-details.component';
import { AddMissionComponent } from './missions/add-mission/add-mission.component';
import { EditMissionComponent } from './missions/edit-mission/edit-mission.component';
import { KidDetailsComponent } from './kids/kid-details/kid-details.component';
import {UserService} from './account/service/user.service';
import {TokenStorageService} from './authentication/services/token-storage.service';
import {AuthService} from './authentication/services/auth.service';
import {HeaderService} from './header/service/header.service';
import {KidService} from './kids/service/kid.service';
import {MissionService} from './missions/services/mission.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SignInComponent,
    SignUpComponent,
    AccountComponent,
    MissionsComponent,
    BiscuitsBoxComponent,
    TreasuresComponent,
    KidsComponent,
    DashboardComponent,
    MissionDetailsComponent,
    AddMissionComponent,
    EditMissionComponent,
    KidDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
  UserService,
  HeaderService,
  TokenStorageService,
  AuthService,
  KidService,
  MissionService  
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    library.add(faCoffee);
    library.add(faCookieBite);
    library.add(faUserPlus);
    library.add(faSignInAlt);
    library.add(faUserSecret);
    library.add(faCheck);
    library.add(faEnvelope);
    library.add(faUnlock);
    library.add(faUserEdit);
    library.add(faEdit);
    library.add(faTimes);
    library.add(faThumbsUp);
    library.add(faPlus);
    library.add(faThumbsDown);
    library.add(faSignOutAlt);
  }
}
