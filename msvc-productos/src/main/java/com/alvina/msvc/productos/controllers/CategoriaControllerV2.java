package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.assemblers.CategoriaModelAssembler;
import com.alvina.msvc.productos.assemblers.ProductoModelAssembler;
import com.alvina.msvc.productos.dtos.ErrorDTO;
import com.alvina.msvc.productos.dtos.RegistroCategoriaDTO;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.models.RegistroCategoria;
import com.alvina.msvc.productos.services.CategoriaService;
import com.alvina.msvc.productos.services.ProductoService;
import com.alvina.msvc.productos.services.RegistroCategoriaService;
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
@RequestMapping("/api/v2/categorias")
@Tag(name = "Categorias V2", description = "Operaciones CRUD de categorias hateoas")
@Validated
public class CategoriaControllerV2 {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;
    private Object toModel;

    @GetMapping
    @Operation(summary = "Obtiene todos las categorias", description = "Devuelve un List de categorias en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Categoria.class)
                    )
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> findAll() {
        List<EntityModel<Categoria>> entityModels = this.categoriaService.findAll()
                .stream()
                .map(categoriaModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Categoria>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(CategoriaControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una categoria", description = "A través del id suministrado devuelve la categoria con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Categoria.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del categoria", required = true)
    })
    public ResponseEntity<EntityModel<Categoria>> findById(@PathVariable Long id){
        EntityModel<Categoria> entityModel = this.categoriaModelAssembler.toModel(
                this.categoriaService.findByCategoriaId(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        categoriaService.deleteByCategoriaId(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    @Operation(
            summary = "Guarda una categoria",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Categoria.class)

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
                    schema = @Schema(implementation = Categoria.class)
            )
    )
    public ResponseEntity<EntityModel<Categoria>> create(@Valid @RequestBody Categoria categoria) {
        Categoria inventarioNew = this.categoriaService.save(categoria);
        EntityModel<Categoria> entityModel = this.categoriaModelAssembler.toModel(inventarioNew);

        return ResponseEntity
                .created(linkTo(methodOn(CategoriaControllerV2.class).findById(inventarioNew.getCategoriaId())).toUri())
                .body(entityModel);
    }
}