import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MycourseseComponent } from './mycoursese.component';

describe('MycourseseComponent', () => {
  let component: MycourseseComponent;
  let fixture: ComponentFixture<MycourseseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MycourseseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MycourseseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
