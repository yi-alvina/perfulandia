package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@RestController
@RequestMapping("/api/v1/productos")
@Validated

public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productoService.findByProductoId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        productoService.deleteByProductoId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoService.save(producto));
    }
}