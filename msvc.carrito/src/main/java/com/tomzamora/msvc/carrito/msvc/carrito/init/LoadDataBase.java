package com.tomzamora.msvc.carrito.msvc.carrito.init;


import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    CarritoRepository carritoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));
        if (carritoRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Carrito carrito = new Carrito();
                carrito.setCantidad(faker.random().nextInt());
                carrito.setIdCarrito(faker.random().nextLong());
                carrito.setIdProducto(faker.random().nextLong());
                carrito.setIdVenta(faker.random().nextLong());
                carrito.setPrecioTotal(faker.random().nextInt());
                carritoRepository.save(carrito);
                log.info(carrito.toString());
            }
        }
    }
}
