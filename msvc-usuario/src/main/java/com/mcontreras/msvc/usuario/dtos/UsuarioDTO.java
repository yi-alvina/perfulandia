package com.mcontreras.msvc.usuario.dtos;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {

    private String run;
    private String nombres;
    private String apellidos;
    private String correo;
}
