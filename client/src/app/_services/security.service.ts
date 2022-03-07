import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Customer } from '../_models/customer';
import { Security } from '../_models/security';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  baseUrl = `${environment.apiUrl}securities`;

  private _securities = new BehaviorSubject<Security[]>([]);

  get securities() {
    return this._securities.asObservable();
  }

  constructor(private http: HttpClient) { }

  fetchSecuritiesByCustomer(){
    return this.http.get<Security[]>(`${this.baseUrl}/self`)
    .pipe(
      map(response => {
        const securities: Security[] = [];
        for(const data in response){
          securities.push(response[data]);
        }
        console.log(securities);
        return securities;
      }),
      tap(securities => {
        this._securities.next(securities);
      })
    );
  }

  getSecurity(id: string){
    return this.http.get<Security>(`${this.baseUrl}/${id}`)
    .pipe(
      map(secData => {
        return secData as Security;
      })
    );
  }

  addSecurity(model: Security){
    let generatedId: string;
    return this.http.post<Security>(this.baseUrl, model)
    .pipe(
      switchMap(secData => {
        generatedId = secData.id;
        return this.securities;
      }),
      take(1),
      tap(securities => {
        model.id = generatedId;
        this._securities.next(securities.concat(model));
      })
    );
  }

  deleteSecurity(id: string){
    return this.http.delete<Security>(`${this.baseUrl}/${id}`)
    .pipe(
      switchMap(() => {
        return this.securities;
      }),
      take(1),
      tap(securities => {
        this._securities.next(securities.filter(b => b.id !== id));
      })
    );
  }
}
