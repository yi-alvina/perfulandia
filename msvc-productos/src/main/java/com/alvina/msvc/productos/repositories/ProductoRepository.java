package com.alvina.msvc.productos.repositories;

import com.alvina.msvc.productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByProductoId(String productoId);

    Optional<Producto> findByPalabraClave(String palabraClave);
}
