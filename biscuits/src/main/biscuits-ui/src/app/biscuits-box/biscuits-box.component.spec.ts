import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BiscuitsBoxComponent } from './biscuits-box.component';

describe('BiscuitsBoxComponent', () => {
  let component: BiscuitsBoxComponent;
  let fixture: ComponentFixture<BiscuitsBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BiscuitsBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BiscuitsBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
