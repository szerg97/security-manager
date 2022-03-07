import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Category } from '../_models/category';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable, of, pipe, throwError } from 'rxjs';
import { catchError, map, switchMap, take, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseUrl = `${environment.apiUrl}security-categories`;

  private _categories = new BehaviorSubject<Category[]>([]);

  get categories() {
    return this._categories.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  fetchCategories(){
    return this.http.get<Category[]>(this.baseUrl)
    .pipe(
      map(response => {
        const categories: Category[] = [];
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

  getCategory(id: string){
    return this.http.get<Category>(`${this.baseUrl}/${id}`)
    .pipe(
      map(categoryData => {
        return categoryData as Category;
      })
    );
  }

  addCategory(model: Category){
    let generatedId: string;
    return this.http.post<Category>(this.baseUrl, model)
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

  deleteCategory(id: string){
    return this.http.delete<Category>(`${this.baseUrl}/${id}`)
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

  updateCategory(id: string, name: string, description: string) {
    let updatedCategories: Category[];
    return this.categories.pipe(
      take(1),
      switchMap(categories => {
        if (!categories || categories.length <= 0) {
          return this.fetchCategories();
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
        } as Category;
        
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
