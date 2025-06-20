package com.alvina.msvc.productos.Services;

import com.alvina.msvc.productos.models.Producto;
import com.alvina.msvc.productos.repositories.ProductoRepository;
import com.alvina.msvc.productos.services.ProductoService;
import com.alvina.msvc.productos.services.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoTest;

    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.productoTest = new Producto(
                productoTest.setProductoId(Long.valueOf(1L));
                productoTest.setNombreProducto("ProductoTest");
                productoTest.setPalabraClave("PalabraClaveTest");
                productoTest.setPrecio(3200);
                productoTest.setCategoria("Categoria Test");
        );
        Faker faker = new Faker
    }
}
