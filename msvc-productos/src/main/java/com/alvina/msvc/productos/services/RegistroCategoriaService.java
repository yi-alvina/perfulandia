package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.models.RegistroCategoria;
import jakarta.validation.Valid;

import java.util.List;

public interface RegistroCategoriaService {
    List<RegistroCategoria> findAll();
    RegistroCategoria findById(Long id);
    RegistroCategoria save(@Valid RegistroCategoria registroCategoria);
    void deleteById(Long id);
}
