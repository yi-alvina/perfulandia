package com.alvina.msvc.inventario.msvc_inventario.services;

import com.alvina.msvc.inventario.msvc_inventario.exceptions.InventarioException;
import com.alvina.msvc.inventario.msvc_inventario.models.Inventario;
import com.alvina.msvc.inventario.msvc_inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventarioServicelmpl implements InventarioService{
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
        return inventarioRepository.findById(Long id).orElseThrow(
                () -> new InventarioException("Inventario con id "+id+" no existe")
        );
    }

    @Transactional
    @Override
    public Inventario save(Inventario inventario) {
        if(inventarioRepository.findByTipoInventarioEquals(inventario.getTipoInventario()).isPresent(

        ))
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        inventarioRepository.deleteById(Long id);
    }
}
