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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/registro-categoria")
@Validated
public class RegistroCategoriaController {
    @Autowired
    private RegistroCategoriaService registroCategoriaService;

    @GetMapping
    public ResponseEntity<List<RegistroCategoria>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registroCategoriaService.findAll(Long id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroCategoria> findByid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registroCategoriaService.findAll(Long id));
    }

    @DeleteMapping("/{id"})
    public ResponseEntity<Void> findByid(@PathVariable Long id) {
        registroCategoriaService.deleteById(Long id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body();
    }

    @PostMapping
    public ResponseEntity<RegistroCategoria> save(@Valid @RequestBody RegistroCategoriaDTO registroCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registroCategoriaService.save(registroCategoria))
    }
