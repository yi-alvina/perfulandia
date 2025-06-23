package com.tomzamora.msvc.carrito.msvc.carrito.assemblers;


import com.tomzamora.msvc.carrito.msvc.carrito.controller.CarritoController;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Producto;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(CarritoController.class).findById(entity.getIdCarrito())).withSelfRel(),
                linkTo(methodOn(CarritoController.class).findAll()).withRel("carrito")

        );

    }
}