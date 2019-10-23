import { Component, OnInit } from '@angular/core';
import {Student} from '../student';
import {Course} from '../course';
import { Router, Route } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-mycoursese',
  templateUrl: './mycoursese.component.html',
  styleUrls: ['./mycoursese.component.css']
})
export class MycourseseComponent implements OnInit {
  public student:Student;
  public course:Course;
  constructor(private router: Router, private http: HttpClient) { }
  Enroll(c_id){
    var DATA = JSON.parse(sessionStorage.getItem('student'));
    this.student = DATA;
    var url = 'http://localhost:8082/course/enroll';
    var body = {
      "student_id" : this.student.student_id,
      "course_id" : c_id
    };

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    this.http.post(url, body, { headers, responseType: 'text' }).subscribe({
      next(val) { alert(val);
        location.reload();
      }
    })
  }

  Drop(c_id){
    var DATA = JSON.parse(sessionStorage.getItem('student'));
    this.student = DATA;
    var url = 'http://localhost:8082/course/drop';
    var body = {
      "student_id" : this.student.student_id,
      "course_id" : c_id
    };

     const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    this.http.post(url, body, { headers, responseType: 'text' }).subscribe({
      next(val) { alert(val);
        location.reload();
      }

    })

  }
  ngOnInit() {
    var DATA = JSON.parse(sessionStorage.getItem('student'));
    this.student = DATA;
    //

    var url = 'http://localhost:8082/course/all';
  
    // The parameters we are gonna pass to the fetch function
  
    var httpOptions = {
      headers: new HttpHeaders({
        'Content-type' : 'application/json'
      })
     };
    this.http.get(url,httpOptions).subscribe(res=>{
      console.log(res);
      var inn = JSON.parse(JSON.stringify(res));
      console.log(inn);
      //console.log(inn);
      this.course = inn;
     // var ee:Course;
    
     for ( var i = 0; i < inn.length;i++)
     {
      //alert(inn[i].students.length);
        for( var j = 0; j < inn[i].students.length; j++){
         // alert(inn[i].students[j].student_id);
          if(inn[i].students[j].student_id == this.student.student_id){
          //  alert("here");
            this.course[i].yes = 1;
          }
        }
     } 
     // for ( var c in this.course){
      //   alert("here2");
      //   for( var d in JSON.parse(c).students){
      //     var s:Student = JSON.parse(d);
      //     alert("here");
      //     if(s.student_id== this.student.student_id){
           
      //       JSON.parse(c).yes = 1;
      //     }

      //   }

      // }

  
      console.log(this.course);
      


  

    });
  }

}
