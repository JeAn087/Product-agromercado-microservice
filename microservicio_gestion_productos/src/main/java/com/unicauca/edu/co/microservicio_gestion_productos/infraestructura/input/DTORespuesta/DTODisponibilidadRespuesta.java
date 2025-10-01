package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que retorna la información de la disponibilidad de un producto
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class DTODisponibilidadRespuesta {
    private boolean disponible;/**True si un producto está disponible, false si no lo está */
    private int stock;/**Cantidad de productos disponibles*/
}
