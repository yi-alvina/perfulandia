package com.alvina.msvc.inventario.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Yileanne Alviña");
        contact.setEmail("yi.alvina@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Inventarios")
                        .version("1.0.0")
                        .description("Este es el microservicio de Inventarios, con el puedes realizar todas las consultas "+
                                " CRUD que necesites")
                        .contact(contact)
                        .summary("esto es un api dentro de un proyecto de MSVC")
    }

}
