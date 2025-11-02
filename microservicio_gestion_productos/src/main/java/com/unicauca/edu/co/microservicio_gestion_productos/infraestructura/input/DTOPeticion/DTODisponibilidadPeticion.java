package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto de valor que represena si un producto está disponible o no y su stock.
 * @author María Camila Hoyos Gómez
 * @author José Guillermo Segura Casas
 * @author Jeison Andrés Vallejo Vallejo
 */

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class DTODisponibilidadPeticion {
    private boolean disponible;/**Permite ver si un producto está disponible o no*/
    private int stock;/**Cantidad de productos disponibles*/
}
