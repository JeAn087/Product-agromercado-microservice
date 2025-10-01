package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de valor que represena si un producto está disponible o no y su stock.
 * @author María Camila Hoyos Gómez
 * @author José Guillermo Segura Casas
 * @author Jeison Andrés Vallejo Vallejo
 */

@Getter
@Setter
public class DTODisponibilidadPeticion {
    private final boolean disponible;/**Permite ver si un producto está disponible o no*/
    private final int stock;/**Cantidad de productos disponibles*/

    public DTODisponibilidadPeticion(boolean disponible, int stock) {
        //TO DO hacer que se valide automáticamente
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo");/*Validación que nos obliga a tener un stock positivo (momentánea) */
        this.disponible = disponible;
        this.stock = stock;
    }

    

}
