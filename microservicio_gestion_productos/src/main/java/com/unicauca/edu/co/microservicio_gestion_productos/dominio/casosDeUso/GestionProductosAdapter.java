package com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionProductosPort;
import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionProductosGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;

/**
 * Servicio de la capa de dominio que gesiona los productos
 * @author Jeison Andrés Vallejo Gómez
 * @author Juan Guillermo Segura Casas
 * @author María Camila Hoyos Gómez
 */

@Service
@Qualifier("crearGestorProductos")
public class GestionProductosAdapter implements GestionProductosPort{

    private final GestionProductosGateway gatewayProductos;

    

    public GestionProductosAdapter(GestionProductosGateway gatewayProductos) {
        this.gatewayProductos = gatewayProductos;
    }

    @Override
    public Producto agregarProducto(Producto prmNuevoProducto) {
        return gatewayProductos.agregarProducto(prmNuevoProducto);
    }

    @Override
    public Producto actualizarProducto(Long prmIdProducto, Producto prmProductoActualizado) {
        return gatewayProductos.actualizarProducto(prmIdProducto, prmProductoActualizado);
    }

    @Override
    public Producto obtenerProductoPorId(Long prmIdProducto) {
        return gatewayProductos.obtenerProductoPorId(prmIdProducto);
    }

    @Override
    public List<Producto> listarProductos() {
        return gatewayProductos.listarProductos();
    }

    @Override
    public Producto deshabilitarProducto(Long prmIdProducto) {
        return gatewayProductos.deshabilitarProducto(prmIdProducto);
    }

}
