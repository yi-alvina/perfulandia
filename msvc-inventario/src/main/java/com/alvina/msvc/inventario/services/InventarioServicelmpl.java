package com.alvina.msvc.inventario.services;

import java.util.List;

import com.alvina.msvc.inventario.clients.ProductoClientsRest;
import com.alvina.msvc.inventario.clients.SucursalClientsRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvina.msvc.inventario.exceptions.InventarioException;
import com.alvina.msvc.inventario.models.entity.Inventario;
import com.alvina.msvc.inventario.repositories.InventarioRepository;

@Service
public class InventarioServicelmpl implements InventarioService {

	private static final Logger log = LoggerFactory.getLogger(InventarioServicelmpl.class);

	@Autowired
	private InventarioRepository inventarioRepository;

	@Autowired
	private ProductoClientsRest productoClientsRest;

	@Autowired
	private SucursalClientsRest sucursalClientsRest;


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
		log.error(String.valueOf(inventario.getInventarioId()));
		if(inventarioRepository.findById(inventario.getInventarioId()).isEmpty()){
			return inventarioRepository.save(inventario);
		}else{
			throw new InventarioException("El inventario con id "+inventario.getInventarioId()+" ya existe en la base de datos");
		}
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		inventarioRepository.deleteById(id);
	}


}
