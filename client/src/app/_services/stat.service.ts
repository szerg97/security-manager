import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Stat1 } from '../_models/stat1';
import { Stat2 } from '../_models/stat2';


@Injectable({
  providedIn: 'root'
})
export class StatService {

  baseUrl1 = `${environment.apiUrl}transactions`;
  baseUrl2 = `${environment.apiUrl}customers`;

  private _stats1 = new BehaviorSubject<Stat1[]>([]);
  private stats2: Stat2;

  get stats1() {
    return this._stats1.asObservable();
  }

  constructor(private http: HttpClient) { }

  fetchStats1(){
    return this.http.get<Stat1[]>(`${this.baseUrl1}/stats`)
    .pipe(
      map(response => {
        const stats: Stat1[] = [];
        for(const data in response){
          stats.push(response[data]);
        }
        console.log(stats);
        return stats;
      }),
      tap(stat => {
        this._stats1.next(stat);
      })
    );
  }

  fetchStats2(){
    return this.http.get<Stat2>(`${this.baseUrl2}/stats`)
    .pipe(
      map(response => {
        this.stats2 = response;
        return this.stats2;
      })
    );
  }
}
