import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, of } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Issuer } from '../_models/issuer';

@Injectable({
  providedIn: 'root'
})
export class DistributorService {

  baseUrl = `${environment.apiUrl}issuers`;

  private _distributors = new BehaviorSubject<Issuer[]>([]);

  get distributors() {
    return this._distributors.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  fetchDistributors(){
    return this.http.get<Issuer[]>(this.baseUrl)
    .pipe(
      map(response => {
        const distributors: Issuer[] = [];
        for(const data in response){
          distributors.push(response[data]);
        }
        console.log(distributors);
        return distributors;
      }),
      tap(distributors => {
        this._distributors.next(distributors);
      })
    );
  }

  getDistributor(id: string){
    return this.http.get<Issuer>(`${this.baseUrl}/${id}`)
    .pipe(
      map(distributorData => {
        return distributorData as Issuer;
      })
    );
  }

  addDistributor(model: Issuer){
    let generatedId: string;
    return this.http.post<Issuer>(this.baseUrl, model)
    .pipe(
      switchMap(distributorData => {
        generatedId = distributorData.id;
        return this.distributors;
      }),
      take(1),
      tap(distributors => {
        model.id = generatedId;
        this._distributors.next(distributors.concat(model));
      })
    );
  }

  deleteDistributor(id: string){
    return this.http.delete<Issuer>(`${this.baseUrl}/${id}`)
    .pipe(
      switchMap(() => {
        return this.distributors;
      }),
      take(1),
      tap(distributors => {
        this._distributors.next(distributors.filter(d => d.id !== id));
      })
    );
  }

  updateDistributor(id: string, name: string, email: string, phone: string, status: boolean) {
    let updatedDistributor: Issuer[];
    return this.distributors.pipe(
      take(1),
      switchMap(distributors => {
        if (!distributors || distributors.length <= 0) {
          return this.fetchDistributors();
        } else {
          return of(distributors);
        }
      }),
      switchMap(distributors => {
        const updatedCategoryIndex = distributors.findIndex(dis => dis.id === id);
        updatedDistributor = [...distributors];
        const oldCategory = updatedDistributor[updatedCategoryIndex];
        updatedDistributor[updatedCategoryIndex] = {
          id: oldCategory.id,
          name: name,
          email: email,
          phone: phone,
          status: status,
        } as Issuer;
        
        return this.http.put(
          `${this.baseUrl}`,
          { ...updatedDistributor[updatedCategoryIndex]}
        );
      }),
      tap(() => {
        this._distributors.next(updatedDistributor);
      })
    );
  }
}
