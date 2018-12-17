import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';
import {faCheck, faArrowRight, faCookieBite, faArrowUp, faPlus, faThumbsDown, faThumbsUp, faUserSecret, faArrowLeft} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {KidService} from './service/kid.service';
import {User} from '../user/model/User';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Kid} from './model/Kid';
import {UserService} from '../user/service/user.service';
import {TokenStorageService} from '../authentication/services/token-storage.service';

@Component({
  selector: 'app-kids',
  templateUrl: './kids.component.html',
  styleUrls: ['./kids.component.css']
})
export class KidsComponent implements OnInit {

    @ViewChild('scroll', { read: ElementRef }) public scroll: ElementRef<any>;

  faCookieBite = faCookieBite;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  faPlus = faPlus;
  faUserSecret = faUserSecret;
  faCheck = faCheck;
  faArrowRight = faArrowRight;
  faArrowLeft = faArrowLeft;
  faArrowUp = faArrowUp;
  kids: Kid[];
  kid: Kid;
  createKidForm: FormGroup;
  submitted = false;
  isCreated = false;
  isNotCreated = false;
  selectedKid: Kid;

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
    this.kid = new Kid ();
    this.kid.nickname = this.createKidForm.controls.nickname.value;
    let user = new User();
    this.userService.getUserById(this.tokenStorageService.getUser())
    .subscribe((userData: User) => {
      user = userData;
      this.kid.user = user;
      console.log(this.kid);
      this.kidService.createKid(this.kid).subscribe(
        data => {
          console.log(data);
          this.isCreated = true;
          this.getKids();
          this.submitted = false;
          this.createKidForm.reset();
        },
        error => {
          console.log(error);
          this.isNotCreated = true;
        }
      );
      },
    error => {
      console.log(error);
      });
  }

  goToMissions(kid: Kid) {
    this.selectedKid = kid;
    this.kidService.toggleIsSelected(this.selectedKid);
    this.router.navigate(['/app/missions']);
  }

  goToTop() {
    let top = document.getElementById('top');
    if (top !== null) {
      top.scrollIntoView();
      top = null;
    }
  }
}
