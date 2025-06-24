package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.models.RegistroCategoria;
import com.alvina.msvc.productos.services.RegistroCategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/registro-categoria")
@Validated
public class RegistroCategoriaController {

    @Autowired
    private RegistroCategoriaService registroCategoriaService;

    @GetMapping
    public ResponseEntity<List<RegistroCategoria>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registroCategoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroCategoria> findByid(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registroCategoriaService.findById(id));

    }

    @PostMapping
    public ResponseEntity<RegistroCategoria> save(@Valid @RequestBody RegistroCategoria registroCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registroCategoriaService.save(registroCategoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroCategoria> delete(@Valid @RequestBody RegistroCategoria registroCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
