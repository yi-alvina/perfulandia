package com.alvina.msvc.inventario.models.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inventarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(description = "Entidad que representa un inventario")
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventario_id", nullable = false, unique = true)
	@Schema(description = "Codigo del inventario", example = "1")
	private Long inventarioId;

	@Column(nullable = false)
	@NotNull(message = "El campo cantidad del Producto no puede ser vacio")
	@Schema(description = "Cantidad del producto en el inventario", example = "32")
	private Integer cantidadProducto;

	private Long idProducto;

	private Long idSucursal;

	public Inventario(Integer cantidadProducto, Long idProducto, Long idSucursal) {
		this.cantidadProducto = cantidadProducto;
		this.idProducto = idProducto;
		this.idSucursal = idSucursal;

	}
}
