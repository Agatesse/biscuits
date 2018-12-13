import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionsTestComponent } from './missions-test.component';

describe('MissionsTestComponent', () => {
  let component: MissionsTestComponent;
  let fixture: ComponentFixture<MissionsTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MissionsTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MissionsTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
