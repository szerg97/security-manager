import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { map, tap } from 'rxjs/operators';
import { User } from '../_models/user';
import { Observable, ReplaySubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = environment.apiUrl;
  private userIsAuth = false;

  private currentUserSource = new ReplaySubject<User>(1);
  currentUser$ = this.currentUserSource.asObservable();

  constructor(
    private http: HttpClient) { }

  get getUserIsAuth(){
    return this.userIsAuth;
  }

  signup(firstName: string, lastName: string, username: string, password: string){
    return this.http.post<User>(this.baseUrl + 'account/register', {firstName: firstName, lastName: lastName, username: username, password: password}).pipe(
      map((user: User) => {
        console.log(user);
        if(user){
          localStorage.setItem('user', JSON.stringify(user));
          this.currentUserSource.next(user);
        }
      })
    );
  }

  login(model: any){
    this.userIsAuth = true;
    return this.http.post(this.baseUrl + 'login', model)
      .pipe(
        map((response: User) => {
          const user = response;
          if (user){
            console.log(user);
            localStorage.setItem('user', JSON.stringify(user));
            this.currentUserSource.next(user);
          }
        })
      );
  }
}
