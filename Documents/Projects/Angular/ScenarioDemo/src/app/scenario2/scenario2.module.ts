import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Child1Component } from './child1/child1.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [Child1Component],
  exports:[Child1Component]
})
export class Scenario2Module { }
