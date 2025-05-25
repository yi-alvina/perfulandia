package com.alvina.msvc.productos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class ProductoCreationDTO {
    @NotNull(message = "El campo nombre de producto no puede ser vacio")
    private String nombreProducto;

    @NotNull(message = "El campo categoria no puede ser vacio")
    private Long categoriaId;

    @NotNull(message = "El campo palabra clave no puede ser vacio")
    private String palabraClave;

    @NotNull(message = "El campo precio no puede estar vacio")
    private Integer precio;



    }

