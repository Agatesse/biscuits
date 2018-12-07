import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreasuresComponent } from './treasures.component';

describe('TreasuresComponent', () => {
  let component: TreasuresComponent;
  let fixture: ComponentFixture<TreasuresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreasuresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreasuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
