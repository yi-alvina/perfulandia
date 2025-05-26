package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.models.Producto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findByProductoId(Long productoId);
    Producto save(@Valid Producto producto);
    void deleteByProductoId(Long productoId);
    Producto updateByProductoId(Long productoId, Producto producto);
    Producto findByPalabraClave(String palabraClave);
    Producto findByNombreProducto(String nombreProducto);
    Producto findByPalabraClaveLike(String palabraClave);
}
