package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOProductoPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOProductoRespuesta;

/**
 * Mapper para convertir entidades de infraestructura a dominio y viceversa para Producto
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Component
public class ProductoInfraDominioMapper {
    public Producto mapearDePeticionAProducto(DTOProductoPeticion prmDtoProductoPeticion)
    {
        if(prmDtoProductoPeticion == null) return null;

        Producto productoReturn = new Producto();

        productoReturn.setProductoId(prmDtoProductoPeticion.getProductoId());
        productoReturn.setProductName(prmDtoProductoPeticion.getProductName());
        productoReturn.setProductDescription(prmDtoProductoPeticion.getProductDescription());

        if(prmDtoProductoPeticion.getProductCategory() != null)
        {
            productoReturn.setProductCategory(prmDtoProductoPeticion.getProductCategory().stream()
            .map(categoria -> new CategoriaInfraDominioMapper().mapearDePeticionACategoria(categoria))
            .toList());
        }

        if(prmDtoProductoPeticion.getProductDisposition() != null) 
        {
            productoReturn.setProductDisposition(new DisponibilidadInfraDominioMapper()
            .mapearDePeticionADisponibilidad(prmDtoProductoPeticion.getProductDisposition()));
        }
        
        return productoReturn;
    }

    public DTOProductoRespuesta mapearDeProductoARespuesta(Producto prmProducto)
    {
        if(prmProducto == null) return null;

        DTOProductoRespuesta productoReturn = new DTOProductoRespuesta();

        productoReturn.setProductoId(prmProducto.getProductoId());
        productoReturn.setProductName(prmProducto.getProductName());
        productoReturn.setProductDescription(prmProducto.getProductDescription());

        if(prmProducto.getProductCategory() != null)
        {
            productoReturn.setProductCategory(new CategoriaInfraDominioMapper()
            .mapearDeListaCategoriaAListaRespuesta(prmProducto.getProductCategory()));
            /* productoReturn.setProductCategory(prmProducto.getProductCategory().stream()
            .map(categoria -> new CategoriaInfraDominioMapper().mapearDeCategoriaARespuesta(categoria))
            .toList()
            ); */
        }

        if(prmProducto.getProductDisposition() != null) 
        {
            productoReturn.setProductDisposition(new DisponibilidadInfraDominioMapper()
            .mapearDisponibilidadRespuesta(prmProducto.getProductDisposition()));
        }
        
        if(prmProducto.getProductZones() != null)
        {
            productoReturn.setProductZones(new ZonaVeredalInfraDominioMapper()
            .mapearDeListaProductoAListaRespuesta(prmProducto.getProductZones()));
        }

        return productoReturn;
    }

    public List<DTOProductoRespuesta> mapearDeListaProductoAListaRespuesta(List<Producto> prmListaProductos)
    {
        if(prmListaProductos == null) return null;

        List<DTOProductoRespuesta> listaReturn = new ArrayList<>();
        for (Producto producto : prmListaProductos) {
            listaReturn.add(this.mapearDeProductoARespuesta(producto));
        }

        return listaReturn;
    }
}
