import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RosterComponent} from './roster/roster.component'
import {MycourseseComponent} from './mycoursese/mycoursese.component';
import {RedirectComponent} from './redirect/redirect.component';
const routes: Routes = [
  { path: 'roster', component: RosterComponent },
  { path: 'mycourse', component: MycourseseComponent},
  { path: 'redirect', component:RedirectComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
