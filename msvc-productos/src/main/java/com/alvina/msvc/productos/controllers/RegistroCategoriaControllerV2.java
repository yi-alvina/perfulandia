package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.assemblers.CategoriaModelAssembler;
import com.alvina.msvc.productos.assemblers.RegistroCategoriaModelAssembler;
import com.alvina.msvc.productos.dtos.ErrorDTO;
import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.models.RegistroCategoria;
import com.alvina.msvc.productos.services.RegistroCategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v2/registro-categoria")
@Validated
public class RegistroCategoriaControllerV2 {
    @Autowired
    private RegistroCategoriaService registroCategoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;
    private Object toModel;
    @Autowired
    private RegistroCategoriaModelAssembler registroCategoriaModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos los registros de categoria", description = "Devuelve un List de registros de categorias en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = RegistroCategoria.class)
                    )
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<RegistroCategoria>>> findAll() {
        List<EntityModel<RegistroCategoria>> entityModels = this.registroCategoriaService.findAll()
                .stream()
                .map(registroCategoriaModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<RegistroCategoria>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(RegistroCategoriaControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un registro de categoria", description = "A través del id suministrado devuelve la categoria registrada con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = RegistroCategoria.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventario no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del registro de categoria", required = true)
    })
    public ResponseEntity<EntityModel<RegistroCategoria>> findById(@PathVariable Long id){
        EntityModel<RegistroCategoria> entityModel = this.registroCategoriaModelAssembler.toModel(
                this.registroCategoriaService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }



    @PostMapping
    @Operation(
            summary = "Guarda un registro de categoria",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = RegistroCategoria.class)

                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El registro de gategoria guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "registro de categoria a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegistroCategoria.class)
            )
    )
    public ResponseEntity<EntityModel<RegistroCategoria>> create(@Valid @RequestBody RegistroCategoria registroCategoria){
        RegistroCategoria registroCategoriaNew = this.registroCategoriaService.save(registroCategoria);
        EntityModel<RegistroCategoria> entityModel = this.registroCategoriaModelAssembler.toModel(registroCategoriaNew);

        return ResponseEntity
                .created(linkTo(methodOn(RegistroCategoriaControllerV2.class).findById(registroCategoriaNew.getregistroId())).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroCategoria> delete(@Valid @RequestBody RegistroCategoriaDTO registroCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
