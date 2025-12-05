import { Injectable, computed, signal, effect } from '@angular/core';

// Definimos la interfaz del producto
export interface CartItem {
  idProducto: number;
  nombre: string;
  precio: number;
  imagen?: string;
  cantidad: number;
}

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  // 1. Estado principal (Señal)
  items = signal<CartItem[]>([]);

  // 2. Computed values (Señales derivadas)
  // Nombre moderno que usas en CarritoComponent
  count = computed(() => this.items().reduce((acc, item) => acc + item.cantidad, 0));
  total = computed(() => this.items().reduce((acc, item) => acc + (item.precio * item.cantidad), 0));

  // --- COMPATIBILIDAD CON CÓDIGO ANTIGUO (CatalogoComponent) ---
  // Estos alias hacen que totalItems() apunte a count(), etc.
  totalItems = this.count;
  totalPrecio = this.total;
  // -------------------------------------------------------------

  constructor() {
    // Recuperar del localStorage al iniciar
    const saved = localStorage.getItem('cart');
    if (saved) {
      try {
        this.items.set(JSON.parse(saved));
      } catch (e) {
        console.error('Error al leer el carrito', e);
      }
    }

    // Guardar en localStorage automáticamente cuando cambie algo
    effect(() => {
      localStorage.setItem('cart', JSON.stringify(this.items()));
    });
  }

  // Función para añadir
  addToCart(producto: any) {
    this.items.update(currentItems => {
      const existingItem = currentItems.find(i => i.idProducto === producto.idProducto);

      if (existingItem) {
        return currentItems.map(i =>
          i.idProducto === producto.idProducto
            ? { ...i, cantidad: i.cantidad + 1 }
            : i
        );
      } else {
        return [...currentItems, {
          idProducto: producto.idProducto,
          nombre: producto.nombre,
          precio: producto.precio,
          imagen: producto.imagen,
          cantidad: 1
        }];
      }
    });
  }

  // Función para restar cantidad
  decrementQuantity(idProducto: number) {
    this.items.update(items => {
      return items.map(item => {
        if (item.idProducto === idProducto) {
          return { ...item, cantidad: item.cantidad - 1 };
        }
        return item;
      }).filter(item => item.cantidad > 0);
    });
  }

  // Función para eliminar un producto completamente
  removeItem(idProducto: number) {
    this.items.update(items => items.filter(i => i.idProducto !== idProducto));
  }

  // Función para vaciar el carrito
  clearCart() {
    this.items.set([]);
  }
}