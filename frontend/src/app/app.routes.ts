import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { DashboardComponent } from './features/admin/dashboard-admin/dashboard-admin.component';
import { DashboardUsuarioComponent } from './features/usuario/dashboard-usuario/dashboard-usuario.component';
import { CatalogoComponent } from './features/catalogo/catalogo.component'; 

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

  // ZONA CLIENTE (Todo unificado en un solo bloque)
  {
    path: 'cliente',
    component: MainLayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardUsuarioComponent },
      { path: 'catalogo', component: CatalogoComponent }, // <--- Aquí está la ruta clave
    ]
  },

  { path: '**', redirectTo: 'login' }
];