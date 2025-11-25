import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser() {
    throw new Error('Method not implemented.');
  }
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth'; 

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
}