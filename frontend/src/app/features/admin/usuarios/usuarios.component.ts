import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


// Material
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Usuario } from '../../../models/usuario';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    CommonModule, FormsModule, MatTableModule, MatButtonModule, 
    MatIconModule, MatInputModule, MatSelectModule, MatCardModule, MatSnackBarModule
  ],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent implements OnInit {
  private userService = inject(UserService);
  private snackBar = inject(MatSnackBar);

  usuarios: Usuario[] = [];
  displayedColumns: string[] = ['id', 'nombre', 'email', 'rol', 'acciones'];
  
  editando = false;
  usuarioForm: Usuario = this.inicializarUsuario();

  ngOnInit() {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.userService.listar().subscribe({
      next: (data) => this.usuarios = data,
      error: (err) => console.error('Error al cargar usuarios', err)
    });
  }

  guardarUsuario() {
    if (this.usuarioForm.id && this.usuarioForm.id !== 0) {
      // ACTUALIZAR
      this.userService.actualizar(this.usuarioForm.id, this.usuarioForm).subscribe({
        next: () => {
          this.mostrarSnack('Usuario actualizado');
          this.cancelarEdicion();
          this.cargarUsuarios();
        },
        error: () => this.mostrarSnack('Error al actualizar')
      });
    } else {
      // CREAR
      this.userService.crear(this.usuarioForm).subscribe({
        next: () => {
          this.mostrarSnack('Usuario creado');
          this.cancelarEdicion();
          this.cargarUsuarios();
        },
        error: () => this.mostrarSnack('Error al crear. ¿Email repetido?')
      });
    }
  }

  borrarUsuario(id: number) {
    if (confirm('¿Estás seguro de borrar este usuario?')) {
      this.userService.borrar(id).subscribe({
        next: () => {
          this.mostrarSnack('Usuario eliminado');
          this.cargarUsuarios();
        },
        error: () => this.mostrarSnack('Error al eliminar')
      });
    }
  }

  editar(usuario: Usuario) {
    this.editando = true;
    this.usuarioForm = { ...usuario, password: '' }; // Copia el usuario, limpia password
  }

  cancelarEdicion() {
    this.editando = false;
    this.usuarioForm = this.inicializarUsuario();
  }

  private inicializarUsuario(): Usuario {
    return {
      id: 0, nombre: '', apellidos: '', email: '', rol: 'CLIENTE',
      direccion: '', codigoPostal: '', password: ''
    };
  }

  private mostrarSnack(mensaje: string) {
    this.snackBar.open(mensaje, 'Cerrar', { duration: 3000 });
  }
}
