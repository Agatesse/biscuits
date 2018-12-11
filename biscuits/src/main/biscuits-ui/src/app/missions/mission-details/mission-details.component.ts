import {Component, Input, OnInit} from '@angular/core';

import {faCookieBite, faEdit, faThumbsDown, faThumbsUp, faCheck} from '@fortawesome/free-solid-svg-icons';
import {Mission} from '../model/Mission';
import {MissionService} from '../services/mission.service';
import {MissionsComponent} from '../missions.component';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Kid} from '../../kids/model/Kid';

@Component({
  selector: 'app-mission-details',
  templateUrl: './mission-details.component.html',
  styleUrls: ['./mission-details.component.css']
})
export class MissionDetailsComponent implements OnInit {

  @Input() kid: Kid;

  faCookieBite = faCookieBite;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  faCheck = faCheck;
  faEdit = faEdit
  updateMissionForm: FormGroup;
  mission: Mission;
  private submitted: boolean = false;
  private isUpdated: boolean = false;
  private isNotUpdated: boolean = false;
  private isEditToggled: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private missionService: MissionService, private missionsComponent: MissionsComponent) {
  }

  ngOnInit() {
    this.updateMissionForm = this.formBuilder.group({
      action: ['', Validators.required],
      biscuits: ['', Validators.required]
    })
  }

  toggleEdit() {
  	this.isEditToggled = !this.isEditToggled;
  }

  get f() { return this.updateMissionForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.updateMissionForm.invalid) {
      return;
    }
    this.mission.action = this.updateMissionForm.controls.action.value;
    this.mission.biscuitsToEarn = this.updateMissionForm.controls.biscuits.value;
    this.missionService.updateMission(this.mission.id, this.mission).subscribe(
      () => {
        this.isUpdated = true;
        console.log(this.isUpdated);
        console.log(this.toggleEdit);
        this.toggleEdit();
        console.log(this.toggleEdit);
      },
      error => {
        console.log(error);
        this.isNotUpdated = true;
      }
    );
  }

  deleteMission() {
  	this.missionService.deleteMission(this.mission.id).subscribe(
  		data => {
  			console.log(data);
  			this.missionsComponent.getMissions();
  		},
  		error => {
  			console.log(error);
  			this.missionsComponent.getMissions();
  		});
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
