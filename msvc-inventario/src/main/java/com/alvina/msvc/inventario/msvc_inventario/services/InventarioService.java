package com.alvina.msvc.inventario.msvc_inventario.services;

import com.alvina.msvc.inventario.msvc_inventario.models.Inventario;

import java.util.List;

public interface InventarioService {
    List<Inventario> findAll();
    Inventario findById(Long id);
    Inventario save(Inventario inventario);
    void deleteById(Long id);
}
