import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Message } from '../_models/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  baseUrl = `${environment.apiUrl}messages/self`;

  private _messages = new BehaviorSubject<Message[]>([]);

  get messages() {
    return this._messages.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  fetchMessages(){
    return this.http.get<Message[]>(this.baseUrl)
    .pipe(
      map(response => {
        const messages: Message[] = [];
        for(const data in response){
          messages.push(response[data]);
        }
        console.log(messages);
        return messages;
      }),
      tap(msgs => {
        this._messages.next(msgs);
      })
    );
  }
}
