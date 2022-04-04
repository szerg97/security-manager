import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Customer } from '../_models/customer';
import { Transaction } from '../_models/transaction';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  baseUrl = `${environment.apiUrl}transactions`;

  private _securities = new BehaviorSubject<Transaction[]>([]);

  get securities() {
    return this._securities.asObservable();
  }

  constructor(private http: HttpClient) { }

  fetchSecuritiesByCustomer(){
    return this.http.get<Transaction[]>(`${this.baseUrl}/self`)
    .pipe(
      map(response => {
        const securities: Transaction[] = [];
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
    return this.http.get<Transaction>(`${this.baseUrl}/${id}`)
    .pipe(
      map(secData => {
        return secData as Transaction;
      })
    );
  }

  addSecurity(model: Transaction){
    let generatedId: string;
    return this.http.post<Transaction>(this.baseUrl, model)
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
    return this.http.delete<Transaction>(`${this.baseUrl}/${id}`)
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
