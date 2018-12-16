import { Component, OnInit, AfterContentInit, OnChanges } from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './model/Mission';
import {MissionService} from './services/mission.service';
import {faCookieBite, faEdit, faPlus, faThumbsDown, faThumbsUp, faExclamationCircle, faCheck} from '@fortawesome/free-solid-svg-icons';
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
export class MissionsComponent implements OnInit, OnChanges, AfterContentInit {
  faPlus = faPlus;
  faCookieBite = faCookieBite;
  faEdit = faEdit;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  faCheck = faCheck;
  faExclamationCircle = faExclamationCircle;
  private missions: Mission[];
  mission: Mission;
  createMissionForm: FormGroup;
  private submitted = false;
  private isCreated = false;
  private isNotCreated = false;
  selectedKid: Kid;
  
  constructor(private missionService: MissionService, private formBuilder: FormBuilder,
    private router: Router, private kidService: KidService, private tokenStorageService: TokenStorageService) { }
    
    ngOnInit() {
      this.getSelectedKid();
      this.createMissionForm = this.formBuilder.group({
        action: ['', Validators.required],
        biscuits: ['', Validators.required]
      });
    }

    ngAfterContentInit() {
      console.log('after content init');
      console.log(this.selectedKid);
    }
    ngOnChanges() {
      console.log('changes');
      console.log(this.selectedKid);
    }
    
    getSelectedKid() {
      this.kidService.updateSelectedKid.subscribe(
        kidData => {
          this.selectedKid = kidData;
          this.getMissions();
          
        },
        error => {
          console.log(error);
        });
      }
      
      getMissions() {
        this.missionService.getMissions(this.selectedKid.id).subscribe((data: Mission[]) => {
          console.log(data);
          this.missions = data;
          console.log( this.missions);
        },
        error => {
          console.log(error);
        });
      }

      reloadMissionsAfterUpdate(isMissionUpdated: boolean) {
            this.getMissions();
      }

      goToKids() {
        this.router.navigate(['/kids']);
      }

      goToTop() {
        let top = document.getElementById('top');
        if (top !== null) {
          top.scrollIntoView();
          top = null;
        }
      }
      
      get f() { return this.createMissionForm.controls; }
      
      onSubmit() {
        this.submitted = true;
        if (this.createMissionForm.invalid) {
          return;
        }
        if (this.createMissionForm.controls.biscuits.value < 1) {
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
