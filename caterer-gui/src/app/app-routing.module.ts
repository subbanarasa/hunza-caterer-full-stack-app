import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CatererListComponent} from "./caterer-list/caterer-list.component";
import {AddCatererComponent} from "./add-caterer/add-caterer.component";
import {CatererDetailsComponent} from "./caterer-details/caterer-details.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'home', component:HomeComponent},
  {path:'caterers', component:CatererListComponent},
  {path:'caterers/add', component:AddCatererComponent},
  {path:'caterers/:id', component:CatererDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
