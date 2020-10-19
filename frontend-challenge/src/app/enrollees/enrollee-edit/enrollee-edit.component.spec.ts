import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolleeEditComponent } from './enrollee-edit.component';

describe('EnrolleeEditComponent', () => {
  let component: EnrolleeEditComponent;
  let fixture: ComponentFixture<EnrolleeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrolleeEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrolleeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
