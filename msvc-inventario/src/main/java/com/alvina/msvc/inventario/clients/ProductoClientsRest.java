package com.alvina.msvc.inventario.clients;

import com.alvina.msvc.productos.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto", url = "localhost:8005/api/v1/productos")
public interface ProductoClientsRest {

    @GetMapping("/{id}")
    Producto findById(@PathVariable int id);
}
