package com.alvina.msvc.inventario.msvc_inventario.models;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mcontreras.msvc.usuario.models.Sucursal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inventarios")
@Getter @Setter @NoArgsConstructor @ToString
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventario_id")
    private Long inventarioId;

    @Column(nullable = false)
    private Integer cantidadProducto;

    private Long idProducto;

    private Long idSucursal;



}
