package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que retorna la información de una categoría
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class DTOCategoriaRespuesta {
    private Long categoryId;/**Identificador único de la categoría */
    private String categoryName;/**Nombre Único de la categoría */
    private String categoryDescription;/**Descripción breve de la categoría*/
}
