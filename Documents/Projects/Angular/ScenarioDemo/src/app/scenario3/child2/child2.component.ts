import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scenario3-child2',
  templateUrl: './child2.component.html',
  styleUrls: ['./child2.component.css']
})
export class Child2Component implements OnInit {

  private sampleChildData:string;

  constructor() { }

  public handleEvent(childData:any){
		this.sampleChildData = childData;
	}

  ngOnInit() {
  }

}
