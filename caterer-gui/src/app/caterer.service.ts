import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Caterer} from "./models/caterer";


@Injectable({
  providedIn: 'root'
})
export class CatererService {

  constructor(private http: HttpClient) {

  }
  private baseUrl = 'http://localhost:8080/api/caterers';

  save(caterer: Caterer): Observable<Caterer> {
    return this.http.post<Caterer>(this.baseUrl, caterer);
  }

  findByName(name: any): Observable<Caterer> {
    return this.http.get<Caterer>(this.baseUrl+'/name/'+name);
  }

  findById(id: any): Observable<Caterer> {
    return this.http.get<Caterer>(this.baseUrl+'/'+id);
  }

  findByCityName(cityName: any): Observable<any> {
    return this.http.get<Caterer>(this.baseUrl+'/location/'+cityName);
  }

}
