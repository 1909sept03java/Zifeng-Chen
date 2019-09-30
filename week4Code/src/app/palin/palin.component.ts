import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-palin',
  templateUrl: './palin.component.html',
  styleUrls: ['./palin.component.css']
})
export class PalinComponent implements OnInit {
  user = {
    message: ''
  };

  constructor() { }

  ngOnInit() {
  }
  palin():void{
    console.log(this.user.message);
    let pal:String = reverse(this.user.message);
    if (pal === this.user.message){
      document.getElementById("output").innerHTML += ("<div> This is a Palindrome<br>Input: " + (this.user.message) + "<br>Output: " + (pal)+"</div>");
      this.user.message = null;
    }
    else
    {
      document.getElementById("output").innerHTML += ("<div> This is NOT a Palindrome<br>Input: " + (this.user.message) + "<br>Output: " + (pal)+"</div>");
      this.user.message = null;
    }

  }
}

function reverse(str:string) {
  return str.split('').reverse().join('');
}