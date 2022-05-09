import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Customer } from '../_models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl = `${environment.apiUrl}customers`;

  private _customers = new BehaviorSubject<Customer[]>([]);

  get customers() {
    return this._customers.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  fetchCustomers(){
    return this.http.get<Customer[]>(this.baseUrl)
    .pipe(
      map(response => {
        const customers: Customer[] = [];
        for(const data in response){
          customers.push(response[data]);
        }
        console.log(customers);
        return customers;
      }),
      tap(data => {
        this._customers.next(data);
      })
    );
  }

  getCustomer(id: string){
    return this.http.get<Customer>(`${this.baseUrl}/${id}`)
    .pipe(
      map(data => {
        return data as Customer;
      })
    );
  }

  addCustomer(model: Customer){
    let generatedId: string;
    return this.http.post<Customer>(this.baseUrl, model)
    .pipe(
      switchMap(data => {
        generatedId = data.id;
        return this.customers;
      }),
      take(1),
      tap(categories => {
        model.id = generatedId;
        this._customers.next(categories.concat(model));
      })
    );
  }

  deleteCustomer(id: string){
    return this.http.delete<Customer>(`${this.baseUrl}/${id}`)
    .pipe(
      switchMap(() => {
        return this.customers;
      }),
      take(1),
      tap(data => {
        this._customers.next(data.filter(b => b.id !== id));
      })
    );
  }
}
