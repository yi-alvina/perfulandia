package com.alvina.msvc.inventario.clients;

import com.mcontreras.msvc.usuario.models.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursal", url = "localhost:8001/api/v1/sucursales")
public interface SucursalClientsRest {

    @GetMapping("/{id}")
    Sucursal findById(@PathVariable int id);
}
