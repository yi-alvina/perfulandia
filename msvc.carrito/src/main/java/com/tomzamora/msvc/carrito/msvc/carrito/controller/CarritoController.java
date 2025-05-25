package com.tomzamora.msvc.carrito.msvc.carrito.controller;

import com.tomzamora.msvc.carrito.msvc.carrito.model.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping
    public ResponseEntity<List<Carrito>> findAll() {
        List<Carrito> carritos = this.carritoRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carritos);
    }
    }
