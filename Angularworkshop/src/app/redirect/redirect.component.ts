import { Component, OnInit } from '@angular/core';
import { Router, Route } from '@angular/router';
@Component({
  selector: 'app-redirect',
  templateUrl: './redirect.component.html',
  styleUrls: ['./redirect.component.css']
})
export class RedirectComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    this.router.navigateByUrl("/mycourse");
  }

}
