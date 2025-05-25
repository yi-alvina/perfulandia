package com.tomzamora.msvc.venta.controller;

import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
@Validated
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> findAll() {
        List<Venta> ventas = this.ventaService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> findById(@PathVariable Long id) {
        Venta venta = this.ventaService.findById();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> save(@RequestBody @Validated Venta venta) {
        Venta saved = this.ventaService.save(venta);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}
