import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { DashboardComponent } from './features/admin/dashboard-admin/dashboard-admin.component';
import { DashboardUsuarioComponent } from './features/usuario/dashboard-usuario/dashboard-usuario.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
   { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    component: MainLayoutComponent, // Comparten el mismo diseño (sidebar)
    children: [
      { path: 'dashboard', component: DashboardComponent },
      // Aquí pondrás luego: 'usuarios', 'configuracion', etc.
    ]
  },

  // 🔵 ZONA CLIENTE (Ruta: /cliente/dashboard)
  {
    path: 'cliente',
    component: MainLayoutComponent, // Comparten diseño (o podrías crear otro layout distinto)
    children: [
      { path: 'dashboard', component: DashboardUsuarioComponent },
      // Aquí pondrás luego: 'mis-pedidos', 'perfil', etc.
    ]
  },

  // Cualquier ruta desconocida vuelve al login
  { path: '**', redirectTo: 'login' }
];