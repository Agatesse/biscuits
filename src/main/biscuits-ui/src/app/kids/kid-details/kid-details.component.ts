import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Kid} from '../model/Kid';
import {KidService} from '../service/kid.service';
import {KidsComponent} from '../kids.component';
import {faArrowRight, faCheck, faCookieBite, faEdit, faThumbsDown, faThumbsUp, faUserSecret} from '@fortawesome/free-solid-svg-icons';


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
  faEdit = faEdit;
  faArrowRight = faArrowRight;
  updateKidForm: FormGroup;
  submitted = false;
  isUpdated = false;
  isNotUpdated = false;
  isEditToggled = false;

  constructor(private formBuilder: FormBuilder, private kidService: KidService, private kidsComponent: KidsComponent) {
  }

  ngOnInit() {
  this.updateKidForm = this.formBuilder.group({
      nickname: ['', Validators.required]
    });
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
        this.toggleEdit();
      },
      error => {
        console.log(error);
        this.isNotUpdated = true;
      }
    );
  }

  deleteKid() {
  this.kidService.deleteKid(this.kid.id).subscribe(
  data => {
        console.log(data);
        this.submitted = false;
  			this.kidsComponent.getKids();
  		},
  		error => {
  			console.log(error);
  			this.kidsComponent.getKids();
  		});
  }
}
