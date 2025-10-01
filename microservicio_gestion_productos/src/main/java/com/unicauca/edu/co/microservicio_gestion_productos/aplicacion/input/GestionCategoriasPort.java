package com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input;

import java.util.List;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;

/**
 * Puerto de entrada para la gestión de categorías, que define los casos de uso disponibles.
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

public interface GestionCategoriasPort {

    Categoria agregarCategoria(Categoria prmNuevaCategoria);

    Categoria actualizarCategoria(Long prmIdCategoria, Categoria prmCategoriaActualizada);

    List<Categoria> listarCategorias();

    Categoria obtenerCategoriaPorId(Long prmIdCategoria);

}
