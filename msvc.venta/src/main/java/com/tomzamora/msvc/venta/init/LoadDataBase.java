package com.tomzamora.msvc.venta.init;


import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase  implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    VentaReporitory ventaReporitory;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));
        if (ventaReporitory.count()==0){
            for  (int i = 0; i < 10; i++) {
                Venta venta = new Venta();
                venta.setIdVenta(faker.random().nextLong());
                venta.setValor(faker.random().nextLong());
                venta.setCantidad(faker.random().nextLong());
                venta.setFecha(LocalDateTime.now());
                venta.setIdSucursal(faker.random().nextLong());
                venta.setIdCliente(faker.random().nextLong());
                ventaReporitory.save(venta);
                log.info(venta.toString());

            }
        }
        }
}
