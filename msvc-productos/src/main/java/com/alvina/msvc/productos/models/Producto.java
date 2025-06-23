package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "Entidad que representa un producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false, unique = true)
	@Schema(description = "Codigo del producto", example = "1")
	private Long productoId;

	@Column(name = "Nombre_producto", nullable = false)
	@NotBlank(message = "El campo nombre de producto no puede ser vacio")
	@Schema(description = "Nombre del producto", example = "Perfume Cielo")
	private String nombreProducto;

	@Column(name = "Palabra_clave", nullable = false)
	@NotBlank(message = "El campo palabra clave de producto no puede ser vacio")
	@Schema(description = "Palabra clave del producto", example = "Juvenil")

	private String palabraClave;

	@Column(name = "Precio", nullable = false)
	@NotNull(message = "El campo precio no puede ser vacio")
	@Schema(description = "Precio del producto", example = "1000")
	private Integer precio;

	@ManyToOne
	@JsonBackReference("categoria-producto")
	@JoinColumn(name = "categoria_producto_id", nullable = false)
	@Schema(description = "Id de la categoria a la que pertenece el producto", example = "1")
	private Categoria categoria;

	public Producto(Long productoId, String nombreProducto, String palabraClave, Integer precio) {
		this.productoId = productoId;
		this.nombreProducto = nombreProducto;
		this.palabraClave = palabraClave;
		this.precio = precio;
	}
}
