import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Parent1Component } from './parent1/parent1.component';
import { Child1Component } from './parent1/child1/child1.component';
import { Subchild1Component } from './parent1/child1/subchild1/subchild1.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [Parent1Component, Child1Component, Subchild1Component],
  exports: [Parent1Component, Child1Component, Subchild1Component]
})
export class Scenario6Module { }
