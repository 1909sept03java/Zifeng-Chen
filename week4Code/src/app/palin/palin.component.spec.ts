import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PalinComponent } from './palin.component';

describe('PalinComponent', () => {
  let component: PalinComponent;
  let fixture: ComponentFixture<PalinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PalinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PalinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
