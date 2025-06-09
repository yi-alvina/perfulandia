package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id", unique = true, nullable = false)
    private Long categoriaId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El campo categoria no puede ser nulo")
    private String nombreCategoria;

}
