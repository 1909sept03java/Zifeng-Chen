import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent implements OnInit {
  user = {
    message: ''
  };
  constructor() { }

  ngOnInit() {
  }
  sort():void{
    var splitted = this.user.message.split(","); 
    var num : number[] = [];
    for( var v in splitted){
      
      if(!isNaN(Number.parseInt(splitted[v]))){
        num.push(Number.parseInt(splitted[v]));
        console.log(splitted[v]);
      }
    }
    var sortedArray: number[] = num.sort((n1,n2) => n1 - n2);
    console.log(sortedArray);
    document.getElementById("output").innerHTML += ("<div> Your Input: " +this.user.message+"<br>Sorted Array: " + (sortedArray)+"</div>");
    this.user.message = null;

  }
}
