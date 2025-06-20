package com.alvina.msvc.inventario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

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
	@Schema(description = "Cantidad del producto en el inventario", example = 32)
	private Integer cantidadProducto;

	private Long idProducto;

	private Long idSucursal;

	public Inventario(Integer cantidadProducto, Long idProducto, Long idSucursal) {
		this.cantidadProducto = cantidadProducto;
		this.idProducto = idProducto;
		this.idSucursal = idSucursal;

	}
}
