package com.alvina.msvc.inventario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventario_id", nullable = false, unique = true)
	private Long inventarioId;

	@Column(nullable = false)
	private Integer cantidadProducto;

	private Long idProducto;

	private Long idSucursal;

}
