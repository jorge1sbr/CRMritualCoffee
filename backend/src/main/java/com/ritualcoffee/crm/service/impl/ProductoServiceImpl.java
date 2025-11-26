package com.ritualcoffee.crm.service.impl;

import com.ritualcoffee.crm.entity.Producto;
import com.ritualcoffee.crm.repository.ProductoRepository;
import com.ritualcoffee.crm.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ======== ZONA PÚBLICA (catálogo) ========

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        // Solo productos activos para el catálogo
        return productoRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return listarTodos();
        }
        return productoRepository.findByCategoriaAndActivoTrue(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarPorId(Integer id) {
        return productoRepository.findByIdProductoAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado o inactivo"));
    }


    // ======== ZONA ADMIN (CRUD) ========

    @Override
    public Producto crear(Producto producto) {
        // Por si acaso, nos aseguramos de que el producto nuevo esté activo
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto datosProducto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(datosProducto.getNombre());
        existente.setDescripcion(datosProducto.getDescripcion());
        existente.setCategoria(datosProducto.getCategoria());
        existente.setTipo(datosProducto.getTipo());
        existente.setPrecio(datosProducto.getPrecio());
        existente.setImagen(datosProducto.getImagen());
        existente.setActivo(datosProducto.getActivo());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminarLogico(Integer id) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setActivo(false);
        productoRepository.save(existente);
    }
}
