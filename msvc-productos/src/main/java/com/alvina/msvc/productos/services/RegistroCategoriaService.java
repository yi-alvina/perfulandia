package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.models.RegistroCategoria;

import java.util.List;

public interface RegistroCategoriaService {
    List<RegistroCategoria> findAll();
    RegistroCategoria findById(Long id);
    RegistroCategoria save(RegistroCategoriaDTO registroCategoria);
    void deleteById(Long id);
}
