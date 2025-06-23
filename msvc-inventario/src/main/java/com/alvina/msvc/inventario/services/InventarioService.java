package com.alvina.msvc.inventario.services;

import java.util.List;

import com.alvina.msvc.inventario.models.entity.Inventario;

public interface InventarioService {
    List<Inventario> findAll();
    Inventario findById(long id);
    Inventario save(Inventario inventario);
    void deleteById(long id);
}
