package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO para la creación o actualización de productos
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor
public class DTOProductoPeticion {
    private Long productoId;/**<!Identificador único del producto */
    private String productName;/**Nombre del producto*/
    private List<DTOCategoriaPeticion> productCategory;/**Uso del agregado*/
    private DTODisponibilidadPeticion productDisposition;/**Uso del objeto de valor*/
    private String productDescription;/**Descripcion del producto */
}
