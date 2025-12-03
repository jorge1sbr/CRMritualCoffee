export interface Producto {
  idProducto: number;
  nombre: string;
  descripcion?: string;
  categoria?: string;
  tipo?: string;
  precio: number;
  imagen?: string;
  activo: boolean;
}