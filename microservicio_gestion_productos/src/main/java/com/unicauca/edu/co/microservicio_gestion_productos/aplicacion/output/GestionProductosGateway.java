package com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output;

import java.util.List;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;

/**
 * Puerto de salida que se conecta a la infraestructura e implementa los CU disponibles de los productos
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

public interface GestionProductosGateway {

    Producto agregarProducto(Producto prmNuevoProducto);

    Producto actualizarProducto(Long prmIdProducto, Producto prmProductoActualizado);

    Producto obtenerProductoPorId(Long prmIdProducto);

    List<Producto> listarProductos();

    Producto deshabilitarProducto(Long prmIdProducto);

}
