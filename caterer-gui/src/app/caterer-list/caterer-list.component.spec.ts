import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatererListComponent } from './caterer-list.component';

describe('CatererListComponent', () => {
  let component: CatererListComponent;
  let fixture: ComponentFixture<CatererListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatererListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatererListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
