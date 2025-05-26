package com.tomzamora.msvc.carrito.msvc.carrito.services;


import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarritoService {

    List<Carrito> findAll();
    Carrito findById(Long id);
    Carrito save(Carrito carrito);
}
