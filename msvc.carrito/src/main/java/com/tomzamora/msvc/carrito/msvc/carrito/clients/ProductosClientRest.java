package com.tomzamora.msvc.carrito.msvc.carrito.clients;

import com.tomzamora.msvc.carrito.msvc.carrito.model.Producto;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc_productos", url= "localhost:8080/api/v1/productos")
public interface ProductosClientRest {

    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);
}
