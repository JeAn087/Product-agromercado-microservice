package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.configuracion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionCategoriaGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionProductosGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso.GestionCategoriasAdapter;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso.GestionProductosAdapter;

/**
 * Aquí se insancian los beans de los adaptadores que usa la capa de dominio
 * para conectarse con la infraestructura.
 * @author Jeison Andrés Vallejo Gómez
 * @author Juan Guillermo Segura Casas
 * @author María Camila Hoyos Gómez
 */

@Configuration
public class BeanConfigurations {

    /**
     * Crea el vean de gestion de casos de uso de productos
     * @param prmGatewayProductoPort
     * @return el gestor de los CU de productos
     */
    @Bean
    @Qualifier("crearGestorProductos")
    public GestionProductosAdapter crearGestorProductos(GestionProductosGateway prmGatewayProductoPort)
    {
        GestionProductosAdapter gestorProductosCU = new GestionProductosAdapter(prmGatewayProductoPort);
        return gestorProductosCU;
    }

    /**
     * Crea el vean de gestion de casos de uso de categorias
     * @param prmGatewayProductoPort
     * @return el gestor de los CU de categorias
     */
    @Bean
    @Qualifier("crearGestorCategorias")
    public GestionCategoriasAdapter crearGestorCategorias(GestionCategoriaGateway prmGatewayCategoriaPort)
    {
        GestionCategoriasAdapter gestorCategoriasCU = new GestionCategoriasAdapter(prmGatewayCategoriaPort);
        return gestorCategoriasCU;
    }


}
