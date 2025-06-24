package com.tomzamora.msvc.carrito.msvc.carrito.controller;


import com.tomzamora.msvc.carrito.msvc.carrito.assemblers.CarritoModelAssembler;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Validated
@Tag(name = "Carritos V2", description = "Operaciones CRUD de carritos hateoas")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler carritoModelAssembler;
    private Object toModel;

    @GetMapping
    @Operation(summary = "Obtiene todos los carritos", description = ("Devuelve un List de carritos en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content (
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Carrito>>> findAll (){
        List<EntityModel<Carrito>> entityModels = this.carritoService.findAll()
                .stream()
                .map(CarritoModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Carrito>> CollectionModel = Co
    }
}
