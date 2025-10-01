package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOCategoriaPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOCategoriaRespuesta;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


/**
 * Mapper para convertir entidades de infraestructura a dominio y viceversa para Categoria
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Component
public class CategoriaInfraDominioMapper {

    public Categoria mapearDePeticionACategoria(DTOCategoriaPeticion prmCategoriaPeticion)
    {
        if(prmCategoriaPeticion == null) return null;

        Categoria categoriaReturn = new Categoria();

        categoriaReturn.setCategoryId(prmCategoriaPeticion.getCategoryId());
        categoriaReturn.setCategoryName(prmCategoriaPeticion.getCategoryName());
        categoriaReturn.setCategoryDescription(prmCategoriaPeticion.getCategoryDescription());

        return categoriaReturn;
    }

    public DTOCategoriaRespuesta mapearDeCategoriaARespuesta(Categoria prmCategoria)
    {
        if(prmCategoria == null) return null;

        DTOCategoriaRespuesta categoriaReturn = new DTOCategoriaRespuesta();

        categoriaReturn.setCategoryId(prmCategoria.getCategoryId());
        categoriaReturn.setCategoryName(prmCategoria.getCategoryName());
        categoriaReturn.setCategoryDescription(prmCategoria.getCategoryDescription());

        return categoriaReturn;
    }

    public List<DTOCategoriaRespuesta> mapearDeListaCategoriaAListaRespuesta(List<Categoria> prmCategorias)
    {
        if(prmCategorias == null) return null;

        List<DTOCategoriaRespuesta> categoriasReturn = new ArrayList<>();

        for(Categoria categoria : prmCategorias)
        {
            categoriasReturn.add(this.mapearDeCategoriaARespuesta(categoria));
        }

        return categoriasReturn;
    }
    

}
