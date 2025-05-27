package com.tomzamora.msvc.carrito.msvc.carrito.controller;

import com.tomzamora.msvc.carrito.msvc.carrito.dtos.CarritoDTO;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")
@Validated
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(carritoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carritoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Carrito> save(@RequestBody @Valid Carrito carrito){
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.save(carrito));
    }

    }
