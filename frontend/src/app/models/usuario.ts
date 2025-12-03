export interface Usuario {
  id: number;
  nombre: string;
  apellidos: string;
  email: string;
  rol: 'ADMIN' | 'CLIENTE';
  direccion: string;
  codigoPostal: string;
  fechaAlta?: string;
  password?: string;  
}
