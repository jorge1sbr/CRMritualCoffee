package com.ritualcoffee.crm.repository;

import com.ritualcoffee.crm.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Listado público: solo productos activos
    List<Producto> findByActivoTrue();

    // Buscar un producto activo por su ID (idProducto)
    Optional<Producto> findByIdProductoAndActivoTrue(Integer idProducto);

    // Filtro opcional por categoría (solo activos)
    List<Producto> findByCategoriaAndActivoTrue(String categoria);
}

