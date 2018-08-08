import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Child1Component } from './child1/child1.component';
import { Child2Component } from './child2/child2.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [Child1Component, Child2Component],
  exports:[Child1Component, Child2Component]
})
export class Scenario3Module { }
