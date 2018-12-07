import { Component, OnInit } from '@angular/core';
import {Mission} from '../model/Mission';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {MissionService} from '../services/mission.service';

@Component({
  selector: 'app-edit-mission',
  templateUrl: './edit-mission.component.html',
  styleUrls: ['./edit-mission.component.css']
})
export class EditMissionComponent implements OnInit {
   faCheck = faCheck;
   mission: Mission;
   editMissionForm: FormGroup;
   isNewMissionFailed: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private missionService: MissionService) {
  }

  ngOnInit() {
    let missionId = window.localStorage.getItem("editMissionId");
    this.missionService.getMissionById(parseInt(missionId))
      .subscribe(data => {
        this.mission = data;
        this.editMissionForm = this.formBuilder.group({
          id: [this.mission.id],
          action: ['', Validators.required],
          biscuits: ['', Validators.required],
         });
      });
    
  }

get f() {
    return this.editMissionForm.controls;
  }

   cancelEditMission() {
    this.router.navigateByUrl('/missions');
  }

  onSubmit() {
    this.missionService.updateMission(this.editMissionForm.value)
      .subscribe(data => {
          console.log(data);
          this.mission = data as Mission;
          this.router.navigate(['missions']);
        },
        error => console.log(error));
  }

}
