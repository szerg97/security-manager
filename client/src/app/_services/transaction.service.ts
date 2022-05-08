import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Transaction } from '../_models/transaction';
import { TransactionExtended } from '../_models/transaction-extended';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  baseUrl = `${environment.apiUrl}transactions`;

  private _securities = new BehaviorSubject<Transaction[]>([]);
  private _transactionsExt = new BehaviorSubject<TransactionExtended[]>([]);

  get securities() {
    return this._securities.asObservable();
  }
  get transactionsExt() {
    return this._transactionsExt.asObservable();
  }

  constructor(private http: HttpClient) { }

  fetchTransactionsByCustomer(){
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

  fetchTransactions(){
    return this.http.get<Transaction[]>(`${this.baseUrl}`)
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

  getTransaction(id: string){
    return this.http.get<Transaction>(`${this.baseUrl}/${id}`)
    .pipe(
      map(secData => {
        return secData as Transaction;
      })
    );
  }

  getTransactionExtended(id: string){
    return this.http.get<TransactionExtended>(`${this.baseUrl}/ext/${id}`)
    .pipe(
      map(secData => {
        return secData as TransactionExtended;
      })
    );
  }

  addTransaction(model: Transaction){
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

  deleteTransaction(id: string){
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
