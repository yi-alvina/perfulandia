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
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroCategoriaServiceImpl implements RegistroCategoriaService{

    private static final Logger log = LoggerFactory.getLogger(RegistroCategoriaServiceImpl.class);


    @Autowired
    private RegistroCategoriaRepository registroCategoriaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RegistroCategoria> findAll(){
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
    public RegistroCategoria save(@Valid RegistroCategoria registroCategoria) {
        log.error(String.valueOf(registroCategoria.getRegistroId()));
        RegistroCategoria registroCategoriaEntity = new RegistroCategoria();
    Categoria categoria = categoriaRepository.findById(
            registroCategoria.getRegistroId()
    ).orElseThrow(
            () -> new CategoriaException("La categoria con id "+ registroCategoria.getRegistroId() + " no existe")
    );

    Producto producto = productoRepository.findById(
            registroCategoria.getRegistroId()
    ).orElseThrow(
        () -> new ProductoException("El producto con id "+ registroCategoria.getProducto() + " no existe")
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
