import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PalinComponent } from './palin/palin.component';
import { SortComponent } from './sort/sort.component';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  { path: 'sort', component: SortComponent },
  { path: 'palin', component: PalinComponent },
  { path: 'home', component: HomeComponent },
];


@NgModule({
  declarations: [
    AppComponent,
    PalinComponent,
    SortComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
     RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
