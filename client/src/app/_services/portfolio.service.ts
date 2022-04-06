import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Portfolio } from '../_models/portfolio';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  fetchSelfPortfolio(){
    return this.http.get<Portfolio>(`${this.baseUrl}portfolios/self`);
  }
}
