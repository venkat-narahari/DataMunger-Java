import { Component, OnInit } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-scenario6-subchild1',
  templateUrl: './subchild1.component.html',
  styleUrls: ['./subchild1.component.css']
})
export class Subchild1Component implements OnInit {

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
