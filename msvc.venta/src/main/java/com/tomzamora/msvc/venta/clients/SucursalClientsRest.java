package com.tomzamora.msvc.venta.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc_sucursal", url= "localhost:8001/api/v1/sucursales")
public interface SucursalClientsRest {

    @GetMapping("/{id}")
    Sucursal findById(@PathVariable Long id);
}
