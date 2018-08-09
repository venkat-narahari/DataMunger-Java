import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Parent1Component } from './parent1/parent1.component';
import { Child1Component } from './parent1/child1/child1.component';
import { Child2Component } from './parent1/child2/child2.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [Parent1Component, Child1Component, Child2Component],
  exports:[Parent1Component, Child1Component, Child2Component]
})
export class Scenario3Module { }
