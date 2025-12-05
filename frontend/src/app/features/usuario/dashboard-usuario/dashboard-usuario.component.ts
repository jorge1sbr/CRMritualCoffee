import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-dashboard-usuario',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule], // Asegúrate de importar estos módulos
  templateUrl: './dashboard-usuario.component.html',
  styleUrls: ['./dashboard-usuario.component.css']
})
export class DashboardUsuarioComponent implements OnInit {
  private http = inject(HttpClient);

  productos: any[] = [];

  ngOnInit() {
    // Cargar productos del backend (esto ya lo tenías o es igual al admin)
    this.http.get<any[]>('http://localhost:8080/api/productos')
      .subscribe(data => this.productos = data);
  }


}