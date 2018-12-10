import {Component, Input, OnInit} from '@angular/core';
import {faCheck, faCookieBite, faEdit, faPlus, faThumbsDown, faThumbsUp, faUserSecret} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {KidService} from './service/kid.service';
import {User} from '../account/model/User';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Kid} from './model/Kid';
import {UserService} from '../account/service/user.service';
import {TokenStorageService} from '../authentication/services/token-storage.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-kids',
  templateUrl: './kids.component.html',
  styleUrls: ['./kids.component.css']
})
export class KidsComponent implements OnInit {

  faCookieBite = faCookieBite;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  private kids: Kid[];
  kid: Kid;
  createKidForm: FormGroup;
  private submitted: boolean = false;
  private isCreated: boolean = false;
  private isNotCreated: boolean = false;

  constructor(private kidService: KidService, private formBuilder: FormBuilder, private router: Router, private userService: UserService,
              private tokenStorageService: TokenStorageService) {

  }

  ngOnInit() {
    this.getKids();
    this.createKidForm = this.formBuilder.group({
      nickname: ['', Validators.required]
    });
  }

  getKids() {
    this.kidService.getKids(this.tokenStorageService.getUser())
      .subscribe((data: Kid[]) => {
            console.log(data);
        this.kids = data;
      },
    error => {
      console.log(error);
    });
  }

  get f() { return this.createKidForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.createKidForm.invalid) {
      return;
    }
    this.kid = new Kid (this.createKidForm.controls.nickname.value, this.tokenStorageService.getUser());
    console.log(this.kid);
    this.kidService.createKid(this.kid).subscribe(
      data => {
        console.log(data);
        this.isCreated = true;
        this.getKids();
      },
      error => {
        console.log(error);
        this.isNotCreated = true;
      }
    );
  }
}
