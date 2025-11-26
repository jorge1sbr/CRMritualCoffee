package com.ritualcoffee.crm.controller;

import com.ritualcoffee.crm.entity.Producto;
import com.ritualcoffee.crm.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200") // para Angular
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Listado catálogo (solo activos)
    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    // Listado por categoría (solo activos)
    @GetMapping("/categoria/{categoria}")
    public List<Producto> listarPorCategoria(@PathVariable String categoria) {
        return productoService.listarPorCategoria(categoria);
    }

    // Detalle de producto (solo si está activo)
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Integer id) {
        return productoService.buscarPorId(id);
    }
}
