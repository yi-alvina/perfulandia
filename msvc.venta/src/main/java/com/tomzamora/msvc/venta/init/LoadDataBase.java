package com.tomzamora.msvc.venta.init;


import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class LoadDataBase {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    VentaReporitory ventaReporitory;
}
