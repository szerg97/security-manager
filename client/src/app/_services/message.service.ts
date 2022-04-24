import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Message } from '../_models/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  baseUrl = `${environment.apiUrl}messages`;

  private _messages = new BehaviorSubject<Message[]>([]);

  get messages() {
    return this._messages.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  addMessage(model: Message){
    let generatedId: string;
    return this.http.post<Message>(this.baseUrl, model)
    .pipe(
      switchMap(secData => {
        generatedId = secData.id;
        return this.messages;
      }),
      take(1),
      tap(securities => {
        model.id = generatedId;
        this._messages.next(securities.concat(model));
      })
    );
  }

  fetchMessages(){
    return this.http.get<Message[]>(`${this.baseUrl}/self`)
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
