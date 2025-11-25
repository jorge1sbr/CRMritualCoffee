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
        next: (res) => {
          this.router.navigate(['/dashboard']);
          
        },
        error: (err) => {
          console.error(err); // Para ver el error real en consola
          this.mensaje = 'Error de credenciales o conexión';
        }
      });
  }
}