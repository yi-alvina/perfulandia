package com.tomzamora.msvc.venta.services;

import com.tomzamora.msvc.venta.model.entities.Venta;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentaService {

    List<Venta> findAll() ;
    Venta findById(Long idVenta);
    Venta save(Venta venta);
}
