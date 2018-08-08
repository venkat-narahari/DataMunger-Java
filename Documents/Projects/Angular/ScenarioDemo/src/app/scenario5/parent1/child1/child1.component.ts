import { Component, OnInit, EventEmitter } from '@angular/core';
import { Output } from '@angular/core';

@Component({
  selector: 'app-scenario5-child1',
  templateUrl: './child1.component.html',
  styleUrls: ['./child1.component.css']
})
export class Child1Component implements OnInit {

  @Output() displayData = new EventEmitter();
  private data:string;

  constructor() { }

  onChange(data:string){
    this.data=data;
    this.displayData.emit(this.data);
  }

  ngOnInit() {
  }

}
