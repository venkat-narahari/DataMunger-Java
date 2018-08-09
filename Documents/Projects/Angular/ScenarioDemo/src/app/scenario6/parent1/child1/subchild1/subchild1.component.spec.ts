import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Subchild1Component } from './subchild1.component';

describe('Subchild1Component', () => {
  let component: Subchild1Component;
  let fixture: ComponentFixture<Subchild1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Subchild1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Subchild1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
