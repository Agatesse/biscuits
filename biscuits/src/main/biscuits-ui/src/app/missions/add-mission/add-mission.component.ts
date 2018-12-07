import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MissionService} from '../services/mission.service';
import {faCheck} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-add-mission',
  templateUrl: './add-mission.component.html',
  styleUrls: ['./add-mission.component.css']
})
export class AddMissionComponent implements OnInit {

  faCheck = faCheck;

  constructor(private formBuilder: FormBuilder,private router: Router, private missionService: MissionService) { }

  private addMissionForm: FormGroup;
  private submitted: boolean = false;

  ngOnInit() {
    this.addMissionForm = this.formBuilder.group({
      id: [],
      action: ['', Validators.required],
      biscuits: ['', Validators.required],
    });
  }

  get f() { return this.addMissionForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.missionService.createMission(this.addMissionForm.value)
      .subscribe( data => {
        this.router.navigate(['missions']);
      });
  }

  cancelAddMission() {
    this.router.navigateByUrl('/mission');
  }

}
