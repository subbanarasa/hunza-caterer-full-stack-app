import { Component, OnInit } from '@angular/core';
import {Caterer} from "../models/caterer";
import {CatererService} from "../caterer.service";

@Component({
  selector: 'app-caterer-list',
  templateUrl: './caterer-list.component.html',
  styleUrls: ['./caterer-list.component.css']
})
export class CatererListComponent implements OnInit {
  caterers: Caterer[] = [];
  cityName: string = "Stockholm";
  error: string | undefined;
  constructor(public catererService: CatererService) { }

  ngOnInit(): void {
    this.fetchCaterers()
  }

  fetchCaterers(){
    this.catererService.findByCityName(this.cityName).subscribe(resp => {
      console.log(resp);
      this.caterers = resp["caterers"];
        this.error = undefined;
      },
    error =>{
      console.log(error.error);
      this.caterers = [];
      this.error = error.error;
    });
  }

  search(){
    this.fetchCaterers()
  }



}
