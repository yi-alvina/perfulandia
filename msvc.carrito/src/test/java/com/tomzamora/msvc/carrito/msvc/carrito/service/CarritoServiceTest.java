package com.tomzamora.msvc.carrito.msvc.carrito.service;


import com.tomzamora.msvc.carrito.msvc.carrito.clients.ProductosClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.clients.VentaClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Producto;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        producto = new Producto(
                id
    }
}
