package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de dominio que representa una categoría de productos en el sistema.
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor
public class Categoria {
    public Categoria() { }
    private Long categoryId;/**Identificador único de la categoría */
    private String categoryName;/**Nombre Único de la categoría */
    private String categoryDescription;/**Descripción breve de la categoria*/
}
