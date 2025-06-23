package com.tomzamora.msvc.carrito.msvc.carrito.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carritoOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Tomas Zamora");
        contact.setEmail("toma.zamora@duocuc.cl"");
                return new OpenAPI()
                .info(new Info()
                .title("Carrito"))
    }
}
