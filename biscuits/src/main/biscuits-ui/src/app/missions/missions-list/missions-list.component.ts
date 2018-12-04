import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {Mission} from "../model/Mission";
import {MissionService} from "../services/mission.service";

@Component({
  selector: 'app-missions-list',
  templateUrl: './missions-list.component.html',
  styleUrls: ['./missions-list.component.css']
})
export class MissionsListComponent implements OnInit {
  private missions: Observable<Mission[]>;

  constructor(private missionService: MissionService) { }

  ngOnInit() {
    this.reloadData();
  }

  removeAllMissions() {
    this.missionService.removeAllMissions()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.missions = this.missionService.retrieveAllMissions();
  }

}
