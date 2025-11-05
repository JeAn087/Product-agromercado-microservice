package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de dominio que representa una zona veredal en el sistema.
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ZonaVeredal {
    private Long zonaId;
    private String nombre;
    private String descripcion;
    private String ubicacion;
}
