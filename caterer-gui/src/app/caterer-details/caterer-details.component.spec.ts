import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatererDetailsComponent } from './caterer-details.component';

describe('CatererDetailsComponent', () => {
  let component: CatererDetailsComponent;
  let fixture: ComponentFixture<CatererDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatererDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatererDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
