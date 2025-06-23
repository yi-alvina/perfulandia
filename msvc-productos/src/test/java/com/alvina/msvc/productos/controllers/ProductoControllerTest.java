package com.alvina.msvc.productos.controllers;

import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = ProductoController.class)
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    public void findAll_terminaOK() throws Exception {
        Producto producto = new Producto(1L,"Nombre","PalabraClave",1000);
        when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/v1/productos")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteById_terminaOK() throws Exception {

        doNothing().when(productoService).deleteByProductoId(1L);

        mockMvc.perform(delete("/api/v1/productos/1")).andExpect(status().isOk());
    }

    @Test
    public void save_terminaOK() throws Exception {

        Producto guardado = new Producto(1L,"Nombre","PalabraClave",1000);
        when(productoService.save(any(Producto.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content("""
				{
				  "nombreProducto": "Nombre",
				  "palabraClave": "PalabraClave",
				  "precio": 1000
				}
				""")).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productoId").value(1L)).andExpect(jsonPath("$.precio").value(1000));
    }

}
