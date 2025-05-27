package com.tomzamora.msvc.venta.clients;


import com.tomzamora.msvc.venta.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc_carrito", url= "localhost:8003/api/v1/carritos")
public interface CarritoClientsRest {

    @GetMapping("/{id}")
    Carrito findById(@PathVariable Long id);
}
