package com.tomzamora.msvc.carrito.msvc.carrito.service;


import com.tomzamora.msvc.carrito.msvc.carrito.clients.ProductosClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.clients.VentaClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Producto;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarritoServiceTest  {

    @Mock
    private ProductosClientRest productosClientRest;

    @Mock
    private VentaClientRest ventaClientRest;

    @InjectMocks
    private CarritoServiceTest carritoServiceTest;

    private Producto producto;
    private Carrito carrito;
    private CarritoService carritoService;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of(:"es", "cl"));
        for (int i = 0; 0; i < 10; i++) {
            Carrito carrito = new Carrito();
            carrito.setIdCarrito();
            carrito.setCantidad("Carrito"+ faker.company().name());
            carrito.setIdProducto();
        }
        this.carritoList.add(carrito);

    }
        this.carritoPrueba = this.carritoList.get(0);
}

@Test
@DisplayName("Encontrar una venta por id")
public void consultarunaVentaPorId(){
    when(this.CarritoReporitory.findAll().thenReturn(this.carritoList));
    List<Carrito> result = carritoServoce.findAll();

    assertThat(result).hasSize(10);
    assertThat(result).contains(this.carritoPrueba);

    verify(CarritoRepository, times (1).findById(idInexistente));
    public void consultarIdDeSucursalInexistente () {
        Long idInexistente = 1L;
        when(CarritoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            CarritoServiceImpl.findById(idInexistente);
        }).isInstanceOf(carritoException.class)
                .hasMessage("Sucursal con el id " + idInexistente + " no encontrado.");
        verify(CarritoRepository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una venta")
    public void debeGuardarUnUsuario() {
        when(CarritoRepository.save(any(Carrito.class))).thenReturn(this.carritoPrueba);
        Carrito result = CarritoServiceImpl.save(this.carritoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.carritoPrueba);
        verify(CarritoRepository,times(1)).save(any(Carrito.class));
    }
    }
}
