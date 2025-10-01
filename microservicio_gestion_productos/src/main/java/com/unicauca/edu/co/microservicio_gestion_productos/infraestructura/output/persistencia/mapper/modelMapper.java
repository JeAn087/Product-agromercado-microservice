package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mapeador entre aplicación y dominio
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */


@Configuration
public class modelMapper {
    @Bean
    public ModelMapper crearMapper()
    {
        return new ModelMapper();
    }

}
