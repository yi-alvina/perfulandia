package com.mcontreras.msvc.usuario.models;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Sucursal {

    private Long idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;

}
