package com.alvina.msvc.inventario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @NoArgsConstructor
public class Inventari0DTO {
    private Long inventarioId;
    private Long sucursalId;
    private Long productoId;
    private Integer cantidadProducto;


}
