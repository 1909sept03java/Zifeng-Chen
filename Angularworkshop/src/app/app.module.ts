import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RosterComponent } from './roster/roster.component';
import { HttpClientModule } from '@angular/common/http';
import { MycourseseComponent } from './mycoursese/mycoursese.component';


@NgModule({
  declarations: [
    AppComponent,
    RosterComponent,
    MycourseseComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
