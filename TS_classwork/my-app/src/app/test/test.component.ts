import { Component, OnInit } from '@angular/core';
import {HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  user = {
message: ''
  
  };
  apiurl = 'https://api.thecatapi.com/v1/images/search';



  constructor(private http:HttpClient)
   {

    }
  input ={
    name : ''
  };
  ngOnInit() {
  }
  //dddddddd
  out():void{
    console.log(this.user.message);
    document.getElementById("comment").innerHTML += (this.user.message) + "<br>";
  
   (this.http.get(this.apiurl)).subscribe((data)=>{
    console.log(data);
    var img = document.createElement("img");
    img.src= data[0].url;
    document.getElementById("comment").innerHTML = '';
    document.getElementById("comment").appendChild(img);
    console.log();
  
  });
  }
  waifu():void{
     let num = (Math.floor((Math.random() * 40000) + 1)).toString();
     let str = 'https://www.thiswaifudoesnotexist.net/example-'+num+'.jpg';
    
      var img = document.createElement("img");
      img.src= str;
      img.style.height='50%';
      img.style.width='50%';
      document.getElementById("comment").innerHTML = '';
      document.getElementById("comment").appendChild(img);
      console.log();

  }
}
