package com.alvina.msvc.productos.repositories;

import com.alvina.msvc.productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {



    Optional<Producto> findByProductoId(Long Id);

    Optional<Producto> findByPalabraClave(String palabraClave);

    Optional<Producto> findByNombreProducto(String nombreProducto);

    Optional<Producto> findByNombreProductoEquals(String nombreProducto);

    List<Producto> findByNombreProductoLike(String nombreProducto);

    List<Producto> findByPalabraClaveLike(String palabraClave);
}