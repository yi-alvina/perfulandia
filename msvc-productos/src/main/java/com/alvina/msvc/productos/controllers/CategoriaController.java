package com.alvina.msvc.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
@Validated
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findByCategoriaId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
		categoriaService.deleteByCategoriaId(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
	}

}
