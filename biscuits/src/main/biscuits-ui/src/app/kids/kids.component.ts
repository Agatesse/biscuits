import { Component, OnInit } from '@angular/core';
import {faCookieBite, faEdit} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-kids',
  templateUrl: './kids.component.html',
  styleUrls: ['./kids.component.css']
})
export class KidsComponent implements OnInit {
faCookieBite = faCookieBite;
  faEdit = faEdit;
  constructor() { }

  ngOnInit() {
  }

}
