package com.alvina.msvc.productos.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import com.alvina.msvc.productos.exceptions.ProductoException;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);


	@Autowired
	private ProductoRepository productoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findByProductoId(Long productoId) {
		return productoRepository.findById(productoId)
				.orElseThrow(() -> new ProductoException("El producto con id " + productoId + " no existe"));
	}

	@Override
	public Producto findByPalabraClave(String palabraClave) {
		return productoRepository.findByPalabraClave(palabraClave)
				.orElseThrow(() -> new ProductoException("No hay productos bajo la palabra clave: " + palabraClave));
	}

	@Override
	public Producto findByNombreProducto(String nombreProducto) {
		return productoRepository.findByNombreProducto(nombreProducto)
				.orElseThrow(() -> new ProductoException("No hay producto con el nombre " + nombreProducto));
	}

	@Override
	public void deleteByProductoId(Long productoId) {
		productoRepository.deleteById(productoId);
	}

	@Override
	public Producto updateByProductoId(Long productoId, Producto producto) {
		return productoRepository.findById(productoId).map(m -> {
			m.setNombreProducto(producto.getNombreProducto());
			m.setPrecio(producto.getPrecio());
			return productoRepository.save(m);
		}).orElseThrow(() -> new ProductoException("El producto con id " + productoId + " no existe")

		);
	}

	@Transactional
	@Override
	public Producto save(Producto producto) {
		log.error(String.valueOf(producto.getProductoId()));
		if (productoRepository.findByNombreProductoEquals(producto.getNombreProducto()).isPresent()) {
			throw new ProductoException("El producto " + producto.getNombreProducto() + " ya existe");
		}
		return productoRepository.save(producto);
	}

	public Producto findByNombreProductoLike(String palabraClave) {
		for (Producto producto : productoRepository.findByNombreProductoLike(palabraClave)) {
			if (producto.getNombreProducto().contains(palabraClave)) {
				return producto;
			}
			return null;
		}

		return null;
	}

	public Producto findByPalabraClaveLike(String palabraClave) {
		for (Producto producto : productoRepository.findByPalabraClaveLike(palabraClave)) {
			if (producto.getPalabraClave().contains(palabraClave)) {
				return producto;
			}
			return null;
		}

		return null;
	}

}