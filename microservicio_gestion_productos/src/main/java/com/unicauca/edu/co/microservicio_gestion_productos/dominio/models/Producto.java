package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de dominio que representa un producto en el sistema.
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Getter
@Setter
@AllArgsConstructor
public class Producto {
    public Producto() {}//Constructor vacio
    private Long productoId;/**<!Identificador único del producto */
    private String productName;/**Nombre del producto*/
    private List<Categoria> productCategory;/**Uso del agregado*/
    private Disponibilidad productDisposition;/**Uso del objeto de valor*/
    private String productDescription;/**Descripcion del producto */

}
