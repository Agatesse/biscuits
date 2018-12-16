import { TestBed } from '@angular/core/testing';

import { KidService } from './kid.service';

describe('KidService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KidService = TestBed.get(KidService);
    expect(service).toBeTruthy();
  });
});
