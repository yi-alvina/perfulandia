package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.assemblers.ProductoModelAssembler;
import com.alvina.msvc.productos.dtos.ErrorDTO;
import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.services.ProductoService;
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
@RequestMapping("/api/v2/producto")
@Validated
@Tag(name = "Productos V2", description = "Operaciones CRUD de productos hateoas")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    ProductoModelAssembler productoModelAssembler;
    private Object toModel;

    @GetMapping
    @Operation(summary = "Obtiene todos los productos", description = "Devuelve un List de productos en el Body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = {@Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )}
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Producto>>> findAll() {
        List<EntityModel<Producto>> entityModels = this.productoService.findAll()
                .stream()
                .map(productoModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Producto>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto", description = "A través del id suministrado devuelve el producto con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<EntityModel<Producto>> findById(@PathVariable Long id){
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(
                this.productoService.findByProductoId(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @GetMapping("/producto/{nombreProducto}")
    @Operation(summary = "Obtiene un producto", description = "A través del id suministrado devuelve el producto con ese nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="Nombre producto", description = "Este es el nombre del producto", required = true)
    })
    public ResponseEntity<EntityModel<Producto>> findByNombreProducto(@PathVariable String nombreProducto){
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(
                this.productoService.findByNombreProducto(nombreProducto)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @GetMapping("/palabra/{palabraClave}")
    @Operation(summary = "Obtiene un producto", description = "A través la palabra clave suministrada devuelve el producto con esa palabra clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado, con la palabra clave suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="palabra clave", description = "Esta es la palabra clave del producto", required = true)
    })
    public ResponseEntity<EntityModel<Producto>> findByPalabraClave(@PathVariable String palabraClave){
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(
                this.productoService.findByPalabraClave(palabraClave)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un Producto",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)

                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El producto guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "producto a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class)
            )
    )
    public ResponseEntity<EntityModel<Producto>> create(@Valid @RequestBody Producto producto){
        Producto productoNew = this.productoService.save(producto);
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(productoNew);

        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).findById(productoNew.getProductoId())).toUri())
                .body(entityModel);
    }

    @GetMapping("/palabra-clave/{palabraClave}")
    public ResponseEntity<Producto> findByPalabraClaveLike(@PathVariable String palabraClave) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findByPalabraClaveLike(palabraClave));
    }

    @GetMapping("/nombre-producto/{palabraClave}")
    public ResponseEntity<Producto> findByNombreProductoLike(@PathVariable String palabraClave) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNombreProductoLike(palabraClave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        productoService.deleteByProductoId(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

