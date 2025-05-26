package com.tomzamora.msvc.venta.clients;

import com.tomzamora.msvc.venta.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc_usuarios", url= "localhost:8082/api/v1/usuarios")

public interface UsuarioClienteRest {
    @GetMapping ("/{id}")
    Usuario findById(@PathVariable Long id);
}
