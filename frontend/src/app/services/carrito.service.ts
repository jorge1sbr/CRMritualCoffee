import { computed, effect, Injectable, signal } from '@angular/core';
import { Producto } from '../models/producto';

export interface CartItem {
  producto: Producto;
  cantidad: number;
}
@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  cartItems = signal<CartItem[]>([]);
  totalItems = computed(() => this.cartItems().reduce((acc, item) => acc + item.cantidad, 0));
  totalPrecio = computed(() => this.cartItems().reduce((acc, item) => acc + (item.producto.precio * item.cantidad), 0));

  constructor() { 
    const saved = localStorage.getItem('cart');
    if (saved) this.cartItems.set(JSON.parse(saved));
    effect(() => localStorage.setItem('cart', JSON.stringify(this.cartItems())));
  }
  addToCart(producto: Producto) {
    this.cartItems.update(items => {
      const exists = items.find(i => i.producto.idProducto === producto.idProducto);
      if (exists) {
        return items.map(i => i.producto.idProducto === producto.idProducto ? { ...i, cantidad: i.cantidad + 1 } : i);
      }
      return [...items, { producto, cantidad: 1 }];
    });
  }
  decrementQuantity(id: number) {
    this.cartItems.update(items => 
      items.map(i => i.producto.idProducto === id ? { ...i, cantidad: i.cantidad - 1 } : i)
           .filter(i => i.cantidad > 0)
    );
  }
  removeItem(id: number) {
    this.cartItems.update(items => items.filter(i => i.producto.idProducto !== id));
  }

  clearCart() {
    this.cartItems.set([]);
  }
}
