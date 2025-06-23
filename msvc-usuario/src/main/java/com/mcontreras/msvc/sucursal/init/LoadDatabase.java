package com.mcontreras.msvc.sucursal.init;

import com.mcontreras.msvc.sucursal.models.entities.Usuario;
import com.mcontreras.msvc.sucursal.repositories.UsuarioRespository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;


@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private UsuarioRespository usuarioRespository;

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        Random random = new Random();

        //en caso de que no exista nada, se crea el elemento
        if(usuarioRespository.count() == 0){

            for(int i = 0; i < 30; i++){
                Usuario usuario = new Usuario();

                //Generar numero rut fake
                String numeroStr = faker.idNumber().valid().replaceAll("-", "");
                String ultimo = numeroStr.substring(numeroStr.length() - 1);
                String restante  = numeroStr.substring(0, numeroStr.length() - 1);

                //Setear el resto de las propiedades
                usuario.setRun(restante + "-" + ultimo);
                usuario.setNombres(faker.name().firstName());
                usuario.setApellidos(faker.name().lastName());
                usuario.setCorreo(faker.internet().emailAddress());

                log.info("Usuario creado {}", usuario);

            }
        }
    }
}
