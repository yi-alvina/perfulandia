package com.mcontreras.msvc.sucursal.controllers;


import com.mcontreras.msvc.sucursal.assemblers.UsuarioModelAssembler;
import com.mcontreras.msvc.sucursal.servicies.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/usuarios")
@Validated
@Tag(
        name = "Usuario API HATEOAS",
        description = "Aquí se generar todos lo métodos CRUD de usuario"
)
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;


}
