package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.dtos.ProductoCreationDTO;
import com.alvina.msvc.productos.models.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findByProductoId(Long productoId);
    Producto save(ProductoCreationDTO producto);
    void deleteByProductoId(Long productoId);
    Producto updateByProductoId(Long productoId, Producto producto);
    Producto findByPalabraClave(String palabraClave);
    Producto findByNombreProducto(String nombreProducto);
}
