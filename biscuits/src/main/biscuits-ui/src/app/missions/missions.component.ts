import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './model/Mission';
import {MissionService} from './services/mission.service';
import {faCookieBite, faEdit, faPlus, faThumbsDown, faThumbsUp} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';

@Component({
  selector: 'app-missions',
  templateUrl: './missions.component.html',
  styleUrls: ['./missions.component.css']
})
export class MissionsComponent implements OnInit {
  faPlus = faPlus;
  faCookieBite = faCookieBite;
  faEdit = faEdit;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  private missions: Mission[];

  constructor(private missionService: MissionService, private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.missionService.getMissions()
      .subscribe((data: Mission[]) => {
        this.missions = data;
      })
  }

  deleteMission(mission: Mission): void {
    this.missionService.deleteMission(mission.id)
      .subscribe( (data: Mission[]) => {
        this.reloadData();
      },
        error => console.log('ERROR: ' + error));
  };

  /*editMission(mission: Mission): void {
    this.router.navigate(['edit-mission' + mission.id]);
  };*/

  editMission(mission: Mission): void {
 window.localStorage.removeItem("editMissionId");
   window.localStorage.setItem("editMissionId", mission.id.toString());
   this.router.navigate(['edit-mission']);
  };

  addMission(): void {
    this.router.navigate(['add-mission']);
  };
}
