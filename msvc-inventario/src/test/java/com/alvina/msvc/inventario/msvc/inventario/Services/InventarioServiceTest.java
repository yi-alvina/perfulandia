package com.alvina.msvc.inventario.msvc.inventario.Services;

import com.alvina.msvc.inventario.clients.ProductoClientsRest;
import com.alvina.msvc.inventario.clients.SucursalClientsRest;
import com.alvina.msvc.inventario.models.Inventario;
import com.alvina.msvc.inventario.repositories.InventarioRepository;
import com.alvina.msvc.inventario.services.InventarioServicelmpl;
import com.alvina.msvc.productos.models.Producto;
import com.mcontreras.msvc.sucursal.models.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {
    @Mock
    private ProductoClientsRest productoClientsRest;

    @Mock
    private SucursalClientsRest sucursalClientsRest;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServicelmpl inventarioService;

    private Producto productoTest;
    private Sucursal sucursalTest;
    private Inventario inventarioTest;

    @BeforeEach
    public void setUp() {

        inventarioTest = new Inventario(
                inventarioTest.setInventarioId(Long.valueOf(1L));
                inventarioTest.setCantidadProducto(32);
                inventarioTest.setIdProducto(Long.valueOf(1L));
                inventarioTest.setIdSucursal(Long.valueOf(1L));
        )


        productoTest = new Producto(
                productoTest.setProductoId(Long.valueOf(1L));
                productoTest.setNombreProducto("ProductoTest");
                productoTest.setPalabraClave("PalabraClaveTest");
                productoTest.setPrecio(3200);
                productoTest.setCategoria("Categoria Test");
        )
        sucursalTest = new Sucursal(
            sucursalTest.setId(Long.valueOf(1L));
            sucursalTest.setNombreSucursal("SucursalTest");
            sucursalTest.setDireccion("DireccionTest");
        )

    }

    @Test
    @DisplayName("Debe crear un Inventario")
    public void shouldCrearUnInventario() {
        when(productoClientsRest.findById(1L)).thenReturn(this.productoTest);
        when(sucursalClientsRest.findById(1L)).thenReturn(this.sucursalTest);
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(this.inventarioTest);

        Inventario result = inventarioService.save(inventarioTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inventarioTest);

        verify(productoClientsRest, times(1)).findById(Long.valueOf(1L));
        verify(sucursalClientsRest, times(1)).findById(Long.valueOf(1L));
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }


}
