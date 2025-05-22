package com.alvina.msvc.productos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RegistroCategoriaDTO {

    @NotNull(message = "El campo categoria no puede estar vacio")
    private Long productoId;

    @NotNull(message = "El campo de producto no puede estar vacio")
    private Long categoriaId;

    @NotNull(message = "El campo nombre de categoria no puede estar vacio")
    private String nombreCategoria;
}
