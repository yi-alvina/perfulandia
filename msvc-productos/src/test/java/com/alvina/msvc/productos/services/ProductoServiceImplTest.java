package com.alvina.msvc.productos.services;

import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testFindAll() {
        List<Producto> retornoMock = List.of(new Producto(1L,"Nombre","PalabraClave",1000));
        when(productoRepository.findAll()).thenReturn(retornoMock);

        List<Producto> retornoObjetoReal = productoService.findAll();

        assertEquals(retornoMock, retornoObjetoReal);
        assertNotNull(retornoObjetoReal);
        assertEquals(1, retornoObjetoReal.size());
    }

    @Test
    public void findById_terminaOK() {

        // Preparar el mock
        Producto retornoMock = new Producto(1L,"Nombre","PalabraClave",1000);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(retornoMock));

        // Llamar al objeto real
        Producto retornoObjetoReal = productoService.findByProductoId(1L);

        // Evaluar respuesta objeto real
        assertEquals(retornoMock, retornoObjetoReal);
    }

    @Test
    public void save_terminaOK() {

        // Preparar el mock
        Producto retornoMock = new Producto(1L,"Nombre","PalabraClave",1000);
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());
        when(productoRepository.save(retornoMock)).thenReturn(retornoMock);

        // Llamar al objeto real
        Producto retornoObjetoReal = productoService.save(retornoMock);

        // Evaluar respuesta objeto real
        assertEquals(retornoMock, retornoObjetoReal);
    }

    @Test
    public void deleteById_terminaOK() {

        try {
            productoService.deleteByProductoId(1L);
        } catch (Exception e) {
            fail("Falla PU", e);
        }


    }
}
