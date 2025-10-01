package com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output;

import java.util.List;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;

/**
 * Puerto de salida que se conecta a la infraestructura e implementa los CU disponibles de las categorías
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

public interface GestionCategoriaGateway {

    Categoria agregarCategoria(Categoria prmNuevaCategoria);

    Categoria actualizarCategoria(Long prmIdCategoria, Categoria prmCategoriaActualizada);

    List<Categoria> listarCategorias();

    Categoria obtenerCategoriaPorId(Long prmIdCategoria);
    
}
