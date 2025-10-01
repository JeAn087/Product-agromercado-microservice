package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta;

import java.util.List;

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
public class DTOProductoRespuesta {
    private Long productoId;/**<!Identificador único del producto */
    private String productName;/**Nombre del producto*/
    private List<DTOCategoriaRespuesta> productCategory;/**Uso del agregado*/
    private DTODisponibilidadRespuesta productDisposition;/**Uso del objeto de valor*/
    private String productDescription;/**Descripcion del producto */
}
