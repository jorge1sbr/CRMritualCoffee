package com.ritualcoffee.crm.controller;

import com.ritualcoffee.crm.entity.Producto;
import com.ritualcoffee.crm.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/productos")
@CrossOrigin(origins = "http://localhost:4200") // dashboard admin en Angular
public class ProductoAdminController {

    private final ProductoService productoService;

    public ProductoAdminController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear producto (ADMIN)
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto creado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar producto (ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Integer id,
            @RequestBody Producto producto) {

        Producto actualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    // Borrado lógico (ADMIN) → activo = false
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }
}
