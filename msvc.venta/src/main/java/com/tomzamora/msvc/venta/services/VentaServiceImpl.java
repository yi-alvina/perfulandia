package com.tomzamora.msvc.venta.services;

import com.tomzamora.msvc.venta.clients.SucursalClientsRest;
import com.tomzamora.msvc.venta.clients.UsuarioClienteRest;
import com.tomzamora.msvc.venta.exception.VentaException;
import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VentaServiceImpl implements VentaService {

    @Autowired
    private UsuarioClienteRest usuarioClienteRest;

    @Autowired
    private SucursalClientsRest sucursalClientsRest;

    @Autowired
    private VentaReporitory ventaReporitory;

    @Override
    public List<Venta> findAll() {
        return ventaReporitory.findAll() ;
    }

    @Override
    public Venta findById(Long id) {
        return ventaReporitory.findById(id).orElseThrow(
                () -> new VentaException("La venta con el id " + id + " no existe.")
        );
    }



    public Venta save(Venta venta) {
        try {
            this.usuarioClienteRest.findById(venta.getIdCliente());
        } catch (FeignException exception) {
            throw new VentaException("La venta con el id " + venta.getIdCliente() + " no existe.");
        }

        try {
            this.sucursalClientsRest.findById(venta.getIdSucursal());
        } catch (FeignException exception) {
            throw new VentaException("La sucursal con el id " + venta.getIdSucursal() + " no fue encontrado.");
        }
        return ventaReporitory.save(venta);
    }
}
