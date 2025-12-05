import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Producto } from '../../models/producto';
import { RouterLink } from '@angular/router';
import { CarritoService } from '../../services/carrito.service';


@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule, MatSnackBarModule, RouterLink],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent implements OnInit {
  private http = inject(HttpClient);
  public carritoServicio = inject(CarritoService);
  private snackBar = inject(MatSnackBar);
  productos: Producto[] = [];

  ngOnInit() {
    this.http.get<Producto[]>('http://localhost:8080/api/productos')
      .subscribe(data => this.productos = data);
  }

  anadirAlCarrito(producto: Producto) {
    this.carritoServicio.addToCart(producto);
    this.snackBar.open('Producto añadido', 'Cerrar', { duration: 2000 });
  }

}