package com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionCategoriasPort;
import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionCategoriaGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
/**
 * Servicio de la capa de dominio que gesiona las categorías
 * @author Jeison Andrés Vallejo Gómez
 * @author Juan Guillermo Segura Casas
 * @author María Camila Hoyos Gómez
 */


@Service
@Qualifier("crearGestorCategorias")
public class GestionCategoriasAdapter implements GestionCategoriasPort {

    private final GestionCategoriaGateway gatewayCategorias;

    public GestionCategoriasAdapter(GestionCategoriaGateway gatewayCategorias) {
        this.gatewayCategorias = gatewayCategorias;
    }

    @Override
    public Categoria agregarCategoria(Categoria prmNuevaCategoria) {
        return gatewayCategorias.agregarCategoria(prmNuevaCategoria);
    }

    @Override
    public Categoria actualizarCategoria(Long prmIdCategoria, Categoria prmCategoriaActualizada) {
        return gatewayCategorias.actualizarCategoria(prmIdCategoria, prmCategoriaActualizada);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return gatewayCategorias.listarCategorias();
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long prmIdCategoria) {
        return gatewayCategorias.obtenerCategoriaPorId(prmIdCategoria);
    }

}
