package com.mcontreras.msvc.sucursal.controllers;


import com.mcontreras.msvc.sucursal.assemblers.SucursalModelAssembler;
import com.mcontreras.msvc.sucursal.dtos.ErrorDTO;
import com.mcontreras.msvc.sucursal.models.Sucursal;
import com.mcontreras.msvc.sucursal.servicies.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/sucursales")
@Validated
@Tag(name = "Sucursales V2", description = "Operaciones CRUD de sucursales hateOAS")
public class SucursalControllerV2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    SucursalModelAssembler sucursalModelAssembler;


    @GetMapping
    @Operation(summary = "Obtiene todas las sucursales", description = "Devuelve un List de las sucursales en el Body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = {@Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )}
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> findAll() {
        List<EntityModel<Sucursal>> entityModels = this.sucursalService.findAll()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Sucursal>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una sucursal", description = "A través del id suministrado devuelve la sucursal con ese id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sucursal no encontrada, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico de la sucursal", required = true)
    })
    public ResponseEntity<EntityModel<Sucursal>> findById(@PathVariable Long id){
        EntityModel<Sucursal> entityModel = sucursalModelAssembler.toModel(
                this.sucursalService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        sucursalService.deleteSucursalById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    @Operation(
            summary = "Guarda una sucursal",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)

                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El categoria guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "categoria a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class)
            )
    )
    public ResponseEntity<EntityModel<Sucursal>> create(@Valid @RequestBody Sucursal categoria) {
        Sucursal inventarioNew = this.sucursalService.save(categoria);
        EntityModel<Sucursal> entityModel = this.sucursalModelAssembler.toModel(inventarioNew);

        return ResponseEntity
                .created(linkTo(methodOn(SucursalControllerV2.class).findById(inventarioNew.getId())).toUri())
                .body(entityModel);
    }

}
