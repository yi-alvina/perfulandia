package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.exceptions.CategoriaException;
import com.alvina.msvc.productos.exceptions.ProductoException;
import com.alvina.msvc.productos.exceptions.RegistroCategoriaException;
import com.alvina.msvc.productos.models.RegistroCategoria;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.repositories.RegistroCategoriaRepository;
import com.alvina.msvc.productos.repositories.CategoriaRepository;
import com.alvina.msvc.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroCategoriaServiceImpl implements RegistroCategoriaService{
    @Autowired
    private RegistroCategoriaRepository registroCategoriaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RegistroCategoria> findAll(Long id){
        return registroCategoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RegistroCategoria findById(Long id) {
        return registroCategoriaRepository.findById(id).orElseThrow(
                () -> new RegistroCategoriaException("Registro con id "+id+" no encontrado")
        );
    }

    @Transactional
    @Override
    public RegistroCategoria save(RegistroCategoriaDTO registroCategoria) {
        RegistroCategoria registroCategoriaEntity = new RegistroCategoria();
    Categoria categoria = categoriaRepository.findById(
            registroCategoria.getCategoriaId()
    ).orElseThrow(
            () -> new CategoriaException("La categoria con id "+ registroCategoria.getCategoriaId() + registroCategoria.getCategoriaId() + " no existe")
    );

    Producto producto = productoRepository.findById(
            registroCategoria.getProductoId()
    ).orElseThrow(
        () -> new ProductoException("El producto con id "+ registroCategoria.getProductoId() + " no existe")
    );

    registroCategoriaEntity.setCategoria(categoria);
    registroCategoriaEntity.setProducto(producto);
    return registroCategoriaRepository.save(registroCategoriaEntity);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        registroCategoriaRepository.deleteById(id);
    }


}
