import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Stat1 } from '../_models/stat1';

@Injectable({
  providedIn: 'root'
})
export class StatService {

  baseUrl = `${environment.apiUrl}transactions`;

  private _stats = new BehaviorSubject<Stat1[]>([]);

  get stats() {
    return this._stats.asObservable();
  }

  constructor(private http: HttpClient) { }

  fetchStats(){
    return this.http.get<Stat1[]>(`${this.baseUrl}/stats`)
    .pipe(
      map(response => {
        const stats: Stat1[] = [];
        for(const data in response){
          stats.push(response[data]);
        }
        console.log(stats);
        return stats;
      }),
      tap(securities => {
        this._stats.next(securities);
      })
    );
  }
}
