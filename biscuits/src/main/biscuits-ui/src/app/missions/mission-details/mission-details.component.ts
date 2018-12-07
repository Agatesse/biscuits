import {Component, Input, OnInit} from '@angular/core';

import {faCookieBite, faEdit, faThumbsDown, faThumbsUp} from '@fortawesome/free-solid-svg-icons';
import {Mission} from '../model/Mission';
import {MissionService} from '../services/mission.service';
import {MissionsComponent} from '../missions.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-mission-details',
  templateUrl: './mission-details.component.html',
  styleUrls: ['./mission-details.component.css']
})
export class MissionDetailsComponent implements OnInit {

  @Input() mission: Mission;



  constructor(private router: Router, private missionService: MissionService, private missionsComponent: MissionsComponent) {
  }

  ngOnInit() {
  }

  addMission(): void {
      this.router.navigate(['add-mission']);
  }

  updateMission() {
    /*    this.missionService.updateMission(this.mission.id, this.mission.action, this.mission.isDone, this.mission.biscuitsToEarn)
          .subscribe(
            data => {
              console.log(data);
              this.mission = data as Mission;
            },
            error => console.log(error));*/
  }

  removeMission() {
    this.missionService.deleteMission(this.mission.id)
      .subscribe(
        data => {
          console.log(data);
          this.missionsComponent.reloadData();
        },
        error => console.log(error));
  }

  isMissionDone() {
    this.missionService.isMissionDone(this.mission.id)
      .subscribe(
        data => {
          console.log(data);
          this.mission = data as Mission;
          console.log(this.mission.isDone);
        },
        error => console.log(error));
  }
}
