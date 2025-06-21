package com.alvina.msvc.productos.assemblers;

import com.alvina.msvc.productos.controllers.ProductoControllerV2;
import com.alvina.msvc.productos.models.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

    @Override
    public EntityModel<Producto> toModel(Producto entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getProductoId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withRel("productos")

        );
    }
}
