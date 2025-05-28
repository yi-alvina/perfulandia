package com.alvina.msvc.productos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvina.msvc.productos.exceptions.CategoriaException;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.repositories.CategoriaRepository;

@Service
public class CategoriaServicelmpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Categoria findByCategoriaId(Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new CategoriaException("Categoria con id " + categoriaId + " no existe"));
	}

	@Transactional
	@Override
	public Categoria save(Categoria categoria) {
		if (categoriaRepository.findByNombreCategoriaEquals(categoria.getNombreCategoria()).isPresent()) {
			throw new CategoriaException("La categoria " + categoria.getNombreCategoria() + " ya existe");
		}
		return categoriaRepository.save(categoria);
	}

	@Transactional
	@Override
	public void deleteByCategoriaId(Long categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}

}
