package com.mcontreras.msvc.usuario.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El campo run usuario no puede ser vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run usuario debe ser XXXXXXXX-X")
    private String run;

    @Column(nullable = false)
    @NotBlank(message = "El campo nombre usuario no puede ser vacio")
    private String nombres;

    @Column(nullable = false)
    @NotBlank(message = "El campo apellido usuario no puede ser vacio")
    private String apellidos;

    private String correo;

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRun(String run) {
        this.run = run;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getRun() {
        return this.run;
    }
    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }


}


