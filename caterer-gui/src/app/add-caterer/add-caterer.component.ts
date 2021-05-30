import {Component, OnInit} from '@angular/core';
import {Caterer} from "../models/caterer";
import {CatererService} from "../caterer.service";
import {Location} from "../models/location";
import {Capacity} from "../models/capacity";
import {ContactInfo} from "../models/contact-info";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-caterer',
  templateUrl: './add-caterer.component.html',
  styleUrls: ['./add-caterer.component.css']
})
export class AddCatererComponent implements OnInit {
  caterer: Caterer = {};
  location: Location = {};
  capacity: Capacity = {};
  contactInfo: ContactInfo = {};
  error: string | undefined;

  constructor(public catererService: CatererService, private router: Router) {
  }

  ngOnInit(): void {

  }

  addCaterer() {
    this.caterer.location = this.location;
    this.caterer.capacity = this.capacity;
    this.caterer.contactInfo = this.contactInfo;
    console.log(this.caterer);
    this.catererService.save(this.caterer).subscribe(
      response => {
        console.log(response);
        this.error = undefined;
        this.router.navigate(['/caterers']);
      },
      error => {
        console.log(error);
        this.error = JSON.stringify(error.error);
      }
      );

  }

}
