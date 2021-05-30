import {Component, OnInit} from '@angular/core';
import {Caterer} from "../models/caterer";
import {CatererService} from "../caterer.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";

@Component({
  selector: 'app-caterer-details',
  templateUrl: './caterer-details.component.html',
  styleUrls: ['./caterer-details.component.css']
})
export class CatererDetailsComponent implements OnInit {

  caterer: Caterer={};

  constructor(private catererService: CatererService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.catererService.findById(this.route.snapshot.params.id).subscribe(
      data => {
        this.caterer = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });

  }

  back() {
    this.router.navigate(['/caterers']);
  }
}
