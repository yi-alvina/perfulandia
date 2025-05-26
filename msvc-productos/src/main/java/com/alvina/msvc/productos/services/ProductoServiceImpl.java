package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.dtos.ProductoDetalleDTO;
import com.alvina.msvc.productos.exceptions.ProductoException;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.repositories.CategoriaRepository;
import com.alvina.msvc.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    @Override
    public Producto findByProductoId(Long productoId) {
        return productoRepository.findById(productoId).orElseThrow(
                () -> new ProductoException("El producto con id " + productoId + " no existe")
        );
    }

    @Override
    public Producto findByPalabraClave(String palabraClave){
        return productoRepository.findByPalabraClave(palabraClave).orElseThrow(
                () -> new ProductoException("No hay productos bajo la palabra clave: "+ palabraClave)
        );
    }

    @Override
    public Producto findByNombreProducto(String nombreProducto){
        return productoRepository.findByNombreProducto(nombreProducto).orElseThrow(
                () -> new ProductoException("No hay producto con el nombre " + nombreProducto)
        );
    }

    @Override
    public void deleteByProductoId(Long productoId){
        productoRepository.deleteById(productoId);
    }

    @Override
    public Producto updateByProductoId(Long productoId, Producto producto){
        return productoRepository.findById(productoId).map(m-> {
            m.setNombreProducto(producto.getNombreProducto());
            m.setPrecio(producto.getPrecio());
            return productoRepository.save(m);
        }).orElseThrow(
                () ->new ProductoException("El producto con id "+ productoId + " no existe")

        );
        }


    @Transactional
    @Override
    public Producto save(Producto producto){
        if (productoRepository.findByNombreProductoEquals(producto.getNombreProducto()).isPresent()) {
            throw new ProductoException("El producto " + producto.getNombreProducto() + " ya existe");
        }
        return productoRepository.save(producto);
        }

}
