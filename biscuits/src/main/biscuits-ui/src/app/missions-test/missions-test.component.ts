import { Component, OnInit } from '@angular/core';
import { Kid } from '../kids/model/Kid';
import { KidService } from '../kids/service/kid.service';

@Component({
  selector: 'app-missions-test',
  templateUrl: './missions-test.component.html',
  styleUrls: ['./missions-test.component.css']
})
export class MissionsTestComponent implements OnInit {

  selectedKid: Kid;

  constructor(private kidService: KidService) { }

  ngOnInit() {
    this.kidService.updateSelectedKid.subscribe(
      (kidData: Kid) => {
        this.selectedKid = kidData;
        console.log(this.selectedKid);
      });
  }

}
