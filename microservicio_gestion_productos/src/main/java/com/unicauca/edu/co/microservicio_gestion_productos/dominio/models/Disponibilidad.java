package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de valor que representa la disponibilidad de un producto
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor
public class Disponibilidad {
    public Disponibilidad() { }

    private boolean disponible;/**Permite ver si un producto está disponible o no*/
    private int stock;/**Cantidad de productos disponibles*/

    public void setStock(int prmStock)
    {
        this.stock = prmStock;
    }
}
