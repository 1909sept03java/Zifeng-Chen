import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mycoursese',
  templateUrl: './mycoursese.component.html',
  styleUrls: ['./mycoursese.component.css']
})
export class MycourseseComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    var DATA = JSON.parse(sessionStorage.getItem('student'));
    console.log(DATA);
  }

}
