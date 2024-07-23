import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) {}

  generateResponse(sessionId: string, message: string): Observable<string> {
    return this.http.post<string>('/api/chat/generate', { sessionId, message }, { responseType: 'text' as 'json' });
  }
}
