import {Component, Input, OnInit} from '@angular/core';
import {Mission} from '../../missions/model/Mission';
import {Router} from '@angular/router';
import {MissionService} from '../../missions/services/mission.service';
import {MissionsComponent} from '../../missions/missions.component';
import {Kid} from '../model/Kid';
import {KidService} from '../service/kid.service';
import {KidsComponent} from '../kids.component';

@Component({
  selector: 'app-kid-details',
  templateUrl: './kid-details.component.html',
  styleUrls: ['./kid-details.component.css']
})
export class KidDetailsComponent implements OnInit {

  @Input() kid: Kid;

  constructor(private router: Router, private kidService: KidService, private kidsComponent: KidsComponent) {
  }

  ngOnInit() {
  }
}
