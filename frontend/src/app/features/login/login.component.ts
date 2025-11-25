import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule, 
    MatCardModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  email = '';
  password = '';
  mensaje = '';

  hacerLogin() {
    this.mensaje = 'Conectando...';
    this.authService.login({ email: this.email, password: this.password })
      .subscribe({
        next: (usuario: any) => { 
          console.log('RESPUESTA DEL BACKEND:', usuario);

          const rol = usuario.rol; 

          if (rol === 'ADMIN') {
            this.router.navigate(['/admin/dashboard']);
          } else if (rol === 'CLIENTE') {
            this.router.navigate(['/cliente/dashboard']);
          } else {
            this.mensaje = 'Error: Usuario sin rol asignado';
            this.router.navigate(['/login']);
          }
        },
        error: (err) => {
          console.error(err);
          this.mensaje = '❌ Credenciales incorrectas o error de servidor';
        }
      });
  }
}