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

import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
@Validated

public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@GetMapping
	public ResponseEntity<List<Producto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findByProductoId(id));
	}

	@GetMapping("/producto/{nombreProducto}")
	public ResponseEntity<Producto> findByNombreProducto(@PathVariable String nombreProducto) {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNombreProducto(nombreProducto));
	}

	@GetMapping("/palabra/{palabraClave}")
	public ResponseEntity<Producto> findByPalabraClave(@PathVariable String palabraClave) {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findByPalabraClave(palabraClave));
	}

	@GetMapping("/palabra-clave/{palabraClave}")
	public ResponseEntity<Producto> findByPalabraClaveLike(@PathVariable String palabraClave) {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findByPalabraClaveLike(palabraClave));
	}

	@GetMapping("/nombre-producto/{palabraClave}")
	public ResponseEntity<Producto> findByNombreProductoLike(@PathVariable String palabraClave) {
		return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNombreProductoLike(palabraClave));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
		productoService.deleteByProductoId(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
	}
}
