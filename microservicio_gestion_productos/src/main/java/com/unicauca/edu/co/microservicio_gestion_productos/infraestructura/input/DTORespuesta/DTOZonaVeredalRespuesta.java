package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que representa la información mostrada al usuario al consultar un producto
 * @author María Camila Hoyos Gómez
 * @author José Guillermo Segura Casas
 * @author Jeison Andrés Vallejo Vallejo
 */

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class DTOZonaVeredalRespuesta {
    private Long zonaId;
    private String nombre;
    private String descripcion;
    private String ubicacion;
}
