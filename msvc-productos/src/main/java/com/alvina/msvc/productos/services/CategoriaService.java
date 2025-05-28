package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.models.Categoria;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.util.List;


public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findByCategoriaId(Long categoriaId);
    Categoria save (@Valid Categoria categoria);
    void deleteByCategoriaId(Long categoriaId);
}
