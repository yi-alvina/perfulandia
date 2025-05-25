package com.alvina.msvc.productos.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor
public class ProductoDetalleDTO {
    private Long idProducto;
    private String nombreProducto;
    private String idCategoria;
    private String palabraClave;
    private Integer precio;
}
