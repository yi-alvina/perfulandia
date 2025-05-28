package com.alvina.msvc.inventario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvina.msvc.inventario.exceptions.InventarioException;
import com.alvina.msvc.inventario.models.Inventario;
import com.alvina.msvc.inventario.repositories.InventarioRepository;

@Service
public class InventarioServicelmpl implements InventarioService {
	@Autowired
	private InventarioRepository inventarioRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Inventario> findAll() {
		return inventarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Inventario findById(Long id) {
		return inventarioRepository.findById(id)
				.orElseThrow(() -> new InventarioException("Inventario con id " + id + " no existe"));
	}

	@Override
	public Inventario save(Inventario inventario) {
		return null;
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		inventarioRepository.deleteById(id);
	}
}
