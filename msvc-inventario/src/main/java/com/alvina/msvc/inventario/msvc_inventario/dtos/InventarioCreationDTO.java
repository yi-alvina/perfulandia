package com.alvina.msvc.inventario.msvc_inventario.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class InventarioCreationDTO {

    @NotNull(message = "El campo de inventario no puede ser vacio")
    private Long idInventario;

    @NotNull(message = "El campo de sucursal no puede ser vacio")
    private Long idSucursal;

    @NotNull(message = "El campo de producto no puede ser vacio")
    private Long idProducto;

    @NotNull(message = "El campo cantidad de producto no puede estar vacio")
    private int cantidadProducto;


}
