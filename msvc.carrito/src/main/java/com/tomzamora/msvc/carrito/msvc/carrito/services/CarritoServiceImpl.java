package com.tomzamora.msvc.carrito.msvc.carrito.services;

import com.tomzamora.msvc.carrito.msvc.carrito.model.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<Carrito> findAll() {
        return List.of();
    }

    @Override
    public Carrito findById(Long id) {
        return null;
    }

    @Override
    public Carrito save(Carrito carrito) {
        return null;
    }


}

