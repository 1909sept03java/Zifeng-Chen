import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, Route } from '@angular/router';
import {Student} from '../student';
@Component({
  selector: 'app-roster',
  templateUrl: './roster.component.html',
  styleUrls: ['./roster.component.css'],
  //  template:
  //  `

  //  `


})


export class RosterComponent implements OnInit {
  public student:Student; 
  constructor(private router: Router, private http: HttpClient) { 
  }
  accept(student){
    alert(student);
  // alert( this.student.student_id );
    sessionStorage.setItem('student',JSON.stringify(student));
    this.router.navigateByUrl("/mycourse");
  }

  ngOnInit() {

    var url = 'http://localhost:8082/student/all';
  
    // The parameters we are gonna pass to the fetch function
  
    var httpOptions = {
      headers: new HttpHeaders({
        'Content-type' : 'application/json'
      })
     };
    this.http.get(url,httpOptions).subscribe(res=>{
      console.log(res);
      
      var data = JSON.parse(JSON.stringify(res));
      this.student = data;
      console.log(data);
      


  
    });

  }

}
