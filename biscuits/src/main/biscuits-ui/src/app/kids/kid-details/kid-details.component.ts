import {Component, Input, OnInit} from '@angular/core';
import {Mission} from '../../missions/model/Mission';
import {Router} from '@angular/router';
import {MissionService} from '../../missions/services/mission.service';
import {MissionsComponent} from '../../missions/missions.component';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Kid} from '../model/Kid';
import {KidService} from '../service/kid.service';
import {KidsComponent} from '../kids.component';
import {faCheck, faCookieBite, faEdit, faPlus, faThumbsDown, faThumbsUp, faUserSecret} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-kid-details',
  templateUrl: './kid-details.component.html',
  styleUrls: ['./kid-details.component.css']
})
export class KidDetailsComponent implements OnInit {

  @Input() kid: Kid;

  faCookieBite = faCookieBite;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  faEdit = faEdit
  updateKidForm: FormGroup;
  private submitted: boolean = false;
  private isUpdated: boolean = false;
  private isNotUpdated: boolean = false;
  private isEditToggled: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private kidService: KidService, private kidsComponent: KidsComponent) {
  }

  ngOnInit() {
  	this.updateKidForm = this.formBuilder.group({
      nickname: ['', Validators.required]
    })
  }

  toggleEdit() {
  	this.isEditToggled = !this.isEditToggled;
  }

   get f() { return this.updateKidForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.updateKidForm.invalid) {
      return;
    }
    this.kid.nickname = this.updateKidForm.controls.nickname.value;
    this.kidService.updateKid(this.kid.id, this.kid).subscribe(
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

  deleteKid() {
  	console.log(this.kid.id);
  	this.kidService.deleteKid(this.kid.id).subscribe(
  		data => {
  			console.log(data);
  			this.kidsComponent.getKids();
  		},
  		error => {
  			console.log(error);
  			this.kidsComponent.getKids();
  		});
  }
}
