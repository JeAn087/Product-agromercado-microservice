package com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input;

import java.util.List;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;

/**
 * Puerto de entrada para la gestión de productos, que define los casos de uso disponibles.
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

public interface GestionProductosPort {

    Producto agregarProducto(Producto prmNuevoProducto);

    Producto actualizarProducto(Long prmIdProducto, Producto prmProductoActualizado);

    Producto obtenerProductoPorId(Long prmIdProducto);

    List<Producto> listarProductos();

    Producto cambiarDisponibilidad(Long prmIdProducto);

    List<Producto> listarProductosByZonaVeredal(Long prmIdZona);

    List<Producto> listarProductosByCategorias(List<Long> prmIDsCategorias);
}
