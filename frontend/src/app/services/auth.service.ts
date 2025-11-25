import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth'; 

  currentUser = signal<any>(null);

  constructor() {
    // Recupera el usuario si recargas la página
    const userGuardado = localStorage.getItem('user');
    if (userGuardado) {
      this.currentUser.set(JSON.parse(userGuardado));
    }
  }

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
      tap((user: any) => {
        // Guardamos el usuario en la señal y en localStorage
        this.currentUser.set(user); 
        localStorage.setItem('user', JSON.stringify(user));
      })
    );
  }

  logout() {
    this.currentUser.set(null);
    localStorage.removeItem('user');
  }
}