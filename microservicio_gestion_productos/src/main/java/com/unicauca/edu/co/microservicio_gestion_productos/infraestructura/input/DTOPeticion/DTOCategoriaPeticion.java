package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la creación o actualización de categorías
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor
public class DTOCategoriaPeticion {
    private Long categoryId;/**Identificador único de la categoría */
    private String categoryName;/**Nombre Único de la categoría */
    private String categoryDescription;/**Descripción breve de la categoría */
}
