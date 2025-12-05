import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { DashboardComponent } from './features/admin/dashboard-admin/dashboard-admin.component';
import { DashboardUsuarioComponent } from './features/usuario/dashboard-usuario/dashboard-usuario.component';
import { CatalogoComponent } from './features/catalogo/catalogo.component'; 
import { CarritoComponent } from './features/carrito/carrito.component'; 
import { UsuariosComponent } from './features/admin/usuarios/usuarios.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  
  // ZONA ADMIN
  {
    path: 'admin',
    component: MainLayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
    ]
  },
  path: 'admin',
  component: MainLayoutComponent,
  children: [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'usuarios', component: UsuariosComponent },
  ]
},

  // ZONA CLIENTE (Todo unificado en un solo bloque)
  {
    path: 'cliente',
    component: MainLayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardUsuarioComponent },
      { path: 'catalogo', component: CatalogoComponent }, 
      { path: 'carrito', component: CarritoComponent } 
    ]
  },

  { path: '**', redirectTo: 'login' }
];