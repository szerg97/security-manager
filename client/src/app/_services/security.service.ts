import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Security } from '../_models/security';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable, of, pipe, throwError } from 'rxjs';
import { catchError, map, switchMap, take, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  baseUrl = `${environment.apiUrl}securities`;

  private _categories = new BehaviorSubject<Security[]>([]);

  get categories() {
    return this._categories.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  fetchSecurities(){
    return this.http.get<Security[]>(this.baseUrl)
    .pipe(
      map(response => {
        const categories: Security[] = [];
        for(const data in response){
          categories.push(response[data]);
        }
        console.log(categories);
        return categories;
      }),
      tap(categories => {
        this._categories.next(categories);
      })
    );
  }

  getSecurity(id: string){
    return this.http.get<Security>(`${this.baseUrl}/${id}`)
    .pipe(
      map(categoryData => {
        return categoryData as Security;
      })
    );
  }

  addSecurity(model: Security){
    let generatedId: string;
    return this.http.post<Security>(this.baseUrl, model)
    .pipe(
      switchMap(categoryData => {
        generatedId = categoryData.id;
        return this.categories;
      }),
      take(1),
      tap(categories => {
        model.id = generatedId;
        this._categories.next(categories.concat(model));
      })
    );
  }

  deleteSecurity(id: string){
    return this.http.delete<Security>(`${this.baseUrl}/${id}`)
    .pipe(
      switchMap(() => {
        return this.categories;
      }),
      take(1),
      tap(categories => {
        this._categories.next(categories.filter(b => b.id !== id));
      })
    );
  }

  updateSecurity(id: string, name: string, description: string) {
    let updatedCategories: Security[];
    return this.categories.pipe(
      take(1),
      switchMap(categories => {
        if (!categories || categories.length <= 0) {
          return this.fetchSecurities();
        } else {
          return of(categories);
        }
      }),
      switchMap(categories => {
        const updatedCategoryIndex = categories.findIndex(cat => cat.id === id);
        updatedCategories = [...categories];
        const oldCategory = updatedCategories[updatedCategoryIndex];
        updatedCategories[updatedCategoryIndex] = {
          id: oldCategory.id,
          name: name,
          description: description,
        } as Security;
        
        return this.http.put(
          `${this.baseUrl}`,
          { ...updatedCategories[updatedCategoryIndex]}
        );
      }),
      tap(() => {
        this._categories.next(updatedCategories);
      })
    );
  }
}
