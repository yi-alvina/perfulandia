package com.mcontreras.msvc.usuario.controllers;

import com.mcontreras.msvc.usuario.models.entities.Usuario;
import com.mcontreras.msvc.usuario.servicies.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id,@RequestBody @Valid   Usuario usuario) {
        Usuario usuarioUpdate = usuarioService.updateUsuarioById(id, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioUpdate);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
