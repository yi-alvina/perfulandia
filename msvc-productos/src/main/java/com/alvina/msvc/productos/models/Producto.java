package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "productos")
@Getter
@Setter
@ToString
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false, unique = true)
	private Long productoId;

	@Column(name = "Nombre_producto", nullable = false)
	@NotBlank(message = "El campo nombre de producto no puede ser vacio")
	private String nombreProducto;

	@Column(name = "Palabra_clave", nullable = false)
	@NotBlank(message = "El campo palabra clave de producto no puede ser vacio")
	private String palabraClave;

	@Column(name = "Precio", nullable = false)
	@NotNull(message = "El campo precio no puede ser vacio")
	private Integer precio;

	@ManyToOne
	@JsonBackReference("categoria-producto")
	@JoinColumn(name = "categoria_producto_id", nullable = false)
	private Categoria categoria;


}
