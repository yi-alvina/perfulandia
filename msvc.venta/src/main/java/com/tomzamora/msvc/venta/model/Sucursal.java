package com.tomzamora.msvc.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Sucursal {
    private Long id;
    private Long nombreSucursal;
    private Long direccion;
}
