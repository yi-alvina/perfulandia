package com.tomzamora.msvc.carrito.msvc.carrito.clients;


import com.tomzamora.msvc.carrito.msvc.carrito.model.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc_venta", url= "localhost:8004/api/v1/ventas")
public interface VentaClientRest {

    @GetMapping("/{id}")
    Venta findById(@PathVariable Long id);


}
