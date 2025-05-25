package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.repositories.CategoriaRepository;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findByCategoriaId(Long categoriaId);
    Categoria save (Categoria categoria);
    void deleteByCategoriaId(Long categoriaId);
}
