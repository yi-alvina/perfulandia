package com.tomzamora.msvc.venta.services;

import com.tomzamora.msvc.venta.clients.UsuarioClienteRest;
import com.tomzamora.msvc.venta.exception.VentaException;
import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VentaServiceImpl implements VentaService {

    @Autowired
    private UsuarioClienteRest usuarioClienteRest;

    @Autowired
    private VentaReporitory ventaReporitory;

    @Override
    public List<Venta> findAll() {
        return this.findAll() ;
    }

    @Override
    public Venta findById(Long idVenta) {
        return this.ventaReporitory.findById(idVenta).orElseThrow(
                ()-> new VentaException("La venta con el id"+idVenta+"no ha sido realizada")
        );
    }

    @Override
    public Venta save(Venta venta) {
        return this.ventaReporitory.save(venta);

    }
}
