package com.tomzamora.msvc.venta.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Usuario {


    private Long idUsuario;
    private String run;
    private String nombres;
    private String apellidos;
    private String correo;

}
