package com.mcontreras.msvc.sucursal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "sucursales")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long id;

    @NotBlank(message = "El campo nombre sucursal no puede ser vacío")
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;

    @NotBlank(message = "El campo dirección no puede ser vacío")
    @Column(name = "direccion_sucursal")
    private String direccion;
}
