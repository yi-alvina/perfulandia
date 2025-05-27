package com.tomzamora.msvc.venta.controller;

import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ventas")
@RestController
@Validated
public class VentaController{

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public Venta findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( ventaService.findById(id));
    }

    @PostMapping
    public Venta save(@RequestBody @Validated Venta venta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.save(venta));
    }
}
