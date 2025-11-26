package com.ritualcoffee.crm.service;

import com.ritualcoffee.crm.entity.Producto;

import java.util.List;

public interface ProductoService {

    // ======== ZONA PÚBLICA (catálogo) ========

    /**
     * Listar todos los productos visibles para el catálogo.
     * Internamente devolverá solo los productos activos.
     */
    List<Producto> listarTodos();

    /**
     * Listar productos por categoría para el catálogo (solo activos).
     */
    List<Producto> listarPorCategoria(String categoria);

    /**
     * Buscar un producto por ID para mostrar en detalle (solo si está activo).
     */
    Producto buscarPorId(Integer id);

    // ======== ZONA ADMIN (CRUD) ========

    /**
     * Crear un nuevo producto.
     */
    Producto crear(Producto producto);

    /**
     * Actualizar un producto existente.
     */
    Producto actualizar(Integer id, Producto datosProducto);

    /**
     * Borrado lógico de un producto (marcar activo = false).
     */
    void eliminarLogico(Integer id);
}
