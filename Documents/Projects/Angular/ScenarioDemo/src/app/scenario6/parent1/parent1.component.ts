import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scenario6-parent1',
  templateUrl: './parent1.component.html',
  styleUrls: ['./parent1.component.css']
})
export class Parent1Component implements OnInit {

  private data:string;

  constructor() { }

  displayData(data:string) {
        this.data=data;
  }

  ngOnInit() {
  }

}
