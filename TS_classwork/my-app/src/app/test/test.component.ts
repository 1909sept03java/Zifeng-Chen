import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  user = {
message: ''
  
  };
  constructor() { }
  input ={
    name : ''
  };
  ngOnInit() {
  }
  //dddddddd
  out():void{
    console.log(this.user.message);
    document.getElementById("comment").innerHTML += (this.user.message) + "<br>";
  }
}
