import {Component, Input, OnInit} from '@angular/core';
import {Mission} from "../model/Mission";
import {MissionService} from "../services/mission.service";
import {MissionsListComponent} from "../missions-list/missions-list.component";

@Component({
  selector: 'app-mission-details',
  templateUrl: './mission-details.component.html',
  styleUrls: ['./mission-details.component.css']
})
export class MissionDetailsComponent implements OnInit {

  @Input() mission: Mission;

  constructor(private missionService: MissionService, private missionsListComponent : MissionsListComponent) { }

  ngOnInit() {
  }

  updateMission() {
    this.missionService.updateMission(this.mission.id, this.mission.title)
      .subscribe(
        data => {
          console.log(data);
          this.mission = data as Mission;
        },
        error => console.log(error));
  }

  removeMission() {
    this.missionService.removeMission(this.mission.id)
      .subscribe(
        data => {
          console.log(data);
          this.missionsListComponent.reloadData();
        },
        error => console.log(error));
  }

}
