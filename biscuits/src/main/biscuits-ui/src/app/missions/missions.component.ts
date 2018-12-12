import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './model/Mission';
import {MissionService} from './services/mission.service';
import {faCookieBite, faEdit, faPlus, faThumbsDown, faThumbsUp, faCheck} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {Kid} from '../kids/model/Kid';
import {KidService} from '../kids/service/kid.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TokenStorageService} from '../authentication/services/token-storage.service';

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
  faCheck = faCheck;
  private missions: Mission[];
  mission: Mission;
  createMissionForm: FormGroup;
  private kids: Kid[];
/*   kid: Kid; */
  private submitted: boolean = false;
  private isCreated: boolean = false;
  private isNotCreated: boolean = false;
  selectedKid: Kid;

  constructor(private missionService: MissionService, private formBuilder: FormBuilder, private router: Router, private kidService: KidService,
              private tokenStorageService: TokenStorageService) {

  }

  ngOnInit() {
    this.getKids();
    this.createMissionForm = this.formBuilder.group({
      action: ['', Validators.required],
      biscuits: ['', Validators.required]
    });
  }


onSelect(kid: Kid) {
  this.selectedKid = kid;
}

  getKids() {
    this.kidService.getKids(this.tokenStorageService.getUser())
      .subscribe((data: Kid[]) => {
        console.log(data);
        this.kids = data;
        this.selectedKid = this.kids[0];
        this.getMissions();
     },
      error => {
        console.log(error);
    });
  }

/*   getKid() {
    this.kidService.getKid(this.selectedKid.nickname)
    .subscribe((data: Kid) => {
      console.log(data);
    this.kid = data;
    },
error => {
console.log(error);
});
  } */


  getMissions() {
    console.log("kidselect:" + this.selectedKid.id);
    this.missionService.getMissions(this.selectedKid.id)
      .subscribe((data: Mission[]) => {
            console.log(data);
        this.missions = data;
        console.log( this.missions);
      },
    error => {
      console.log(error);
    });
  }

  get f() { return this.createMissionForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.createMissionForm.invalid) {
      return;
    }
    this.mission = new Mission ();
    this.mission.action = this.createMissionForm.controls.action.value;
    this.mission.biscuitsToEarn = this.createMissionForm.controls.biscuits.value;
    this.mission.kid = this.selectedKid;
    console.log(this.mission);
    this.missionService.createMission(this.mission).subscribe(
      data => {
        console.log(data);
        this.isCreated = true;
        this.getMissions();
        this.submitted = false;
        this.createMissionForm.reset();
      },
      error => {
        console.log(error);
        this.isNotCreated = true;
      }
    );
  }
}
