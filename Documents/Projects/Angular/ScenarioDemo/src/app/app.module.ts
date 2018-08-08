import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Scenario1Module } from './scenario1/scenario1.module';
import { Scenario2Module } from './scenario2/scenario2.module';
import { Scenario3Module } from './scenario3/scenario3.module';
import { Scenario4Module } from './scenario4/scenario4.module';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    Scenario1Module,
    Scenario2Module,
    Scenario3Module,
    Scenario4Module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
