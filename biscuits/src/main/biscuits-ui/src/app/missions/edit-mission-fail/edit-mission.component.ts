import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';
import {MissionService} from '../services/mission.service';
import {Mission} from '../model/Mission';
import {first} from 'rxjs/operators';
import {faCheck} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-edit-mission',
  templateUrl: './edit-mission.component.html',
  styleUrls: ['./edit-mission.component.css']
})
export class EditMissionComponent implements OnInit {



  constructor(private formBuilder: FormBuilder, private route: ActivatedRouteSnapshot, private router: Router, private missionService: MissionService) {
  }
  /*faCheck = faCheck;
  mission: Mission;
  editMissionForm: FormGroup;
  isNewMissionFailed: boolean = false;*/

  ngOnInit() {

/*    let missionId = window.localStorage.getItem("editMissionId");*/
    /*if(!missionId) {
      alert("Invalid action.");
      this.router.navigate(['missions']);
      return;
    }*/

    /*this.missionService.getMissionById(parseInt(missionId))
      .subscribe(data => {
        this.mission = data;
      });
*/
    /*    console.log(this.route.params['id']);
     this.missionService.getMissionById(this.route.params['id'])
       .subscribe(data => {this.mission = data; console.log("ok")},
         error => {console.log(error)});*/
  /*this.editMissionForm = this.formBuilder.group({
      id: [this.mission.id],
      action: ['', Validators.required],
      biscuits: ['', Validators.required],
    });*/
  }
/*
  get f() {
    return this.editMissionForm.controls;
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

  cancelEditMission() {
    this.router.navigateByUrl('/missions');
  }*/

}
