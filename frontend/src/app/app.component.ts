import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html', 
  styleUrl: './app.component.css'
})
export class AppComponent {
  authService = inject(AuthService);

  email = 'admin@ritual.com';
  password = '1234';
  usuarioLogueado: any = null;
  mensaje = '';

  hacerLogin() {
    this.mensaje = 'Conectando...';
    const credenciales = { email: this.email, password: this.password };
    
    this.authService.login(credenciales).subscribe({
      next: (res) => {
        this.usuarioLogueado = res;
        this.mensaje = '';
      },
      error: (err) => {
        console.error(err);
        this.mensaje = '❌ Error: No se pudo conectar o credenciales incorrectas.';
      }
    });
  }
}