import { Component, OnInit } from '@angular/core';
import {faCookieBite, faEdit, faThumbsDown, faThumbsUp, faTimes} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-treasures',
  templateUrl: './treasures.component.html',
  styleUrls: ['./treasures.component.css']
})
export class TreasuresComponent implements OnInit {
  faCookieBite = faCookieBite;
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  constructor() { }

  ngOnInit() {
  }

}
