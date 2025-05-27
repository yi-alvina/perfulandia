package com.tomzamora.msvc.venta.services;

import com.tomzamora.msvc.venta.clients.SucursalClientsRest;
import com.tomzamora.msvc.venta.clients.UsuarioClienteRest;
import com.tomzamora.msvc.venta.exception.VentaException;
import com.tomzamora.msvc.venta.model.Sucursal;
import com.tomzamora.msvc.venta.model.Usuario;
import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return this.findAll() ;
    }

    @Override
    public Venta findById(Long id) {
        return this.ventaReporitory.findById(id).orElseThrow(
                () -> new VentaException("El carrito con el id " + id + " no existe.")
        );
    }



    public Venta save(Venta venta) {
        try {
            Usuario usuario = this.usuarioClienteRest.findById(venta.getIdCliente());
        } catch (FeignException exception) {
            throw new VentaException("El carrito con el id " + venta.getIdCliente() + " no existe.");
        }

        try {
            Sucursal sucursal = this.sucursalClientsRest.findById(venta.getIdSucursal());
        } catch (FeignException exception) {
            throw new VentaException("El producto con el id " + venta.getIdSucursal() + " no fue encontrado.");
        }
        return ventaReporitory.save(venta);
    }
}
