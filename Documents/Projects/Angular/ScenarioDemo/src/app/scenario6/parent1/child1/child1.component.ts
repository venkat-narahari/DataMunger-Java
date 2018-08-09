import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scenario6-child1',
  templateUrl: './child1.component.html',
  styleUrls: ['./child1.component.css']
})
export class Child1Component implements OnInit {

  private data:string;

  constructor() { }

  displayData(data:string) {
        this.data=data;
  }

  ngOnInit() {
  }

}