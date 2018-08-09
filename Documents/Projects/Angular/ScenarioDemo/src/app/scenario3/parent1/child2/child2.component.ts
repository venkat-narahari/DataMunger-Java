import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scenario3-child2',
  templateUrl: './child2.component.html',
  styleUrls: ['./child2.component.css']
})
export class Child2Component implements OnInit {

  private data:string;

  constructor() { }

  displayData(data:string) {
        this.data=data;
  }

  ngOnInit() {
  }

}
