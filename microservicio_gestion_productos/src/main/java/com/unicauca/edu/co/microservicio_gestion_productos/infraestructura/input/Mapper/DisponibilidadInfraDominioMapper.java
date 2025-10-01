package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTODisponibilidadPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTODisponibilidadRespuesta;

/**
 * Mapper para convertir entidades de infraestructura a dominio y viceversa para la Disponibilidad
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */
@Component
public class DisponibilidadInfraDominioMapper {

    public Disponibilidad mapearDePeticionADisponibilidad(DTODisponibilidadPeticion prmDisponibilidadPeticion)
    {
        if(prmDisponibilidadPeticion == null) return null;

        Disponibilidad disponibilidadReturn = new Disponibilidad();

        disponibilidadReturn.setDisponible(prmDisponibilidadPeticion.isDisponible());
        disponibilidadReturn.setStock(prmDisponibilidadPeticion.getStock());

        return disponibilidadReturn;
    }

    public DTODisponibilidadRespuesta mapearDisponibilidadRespuesta(Disponibilidad prmDisponibilidad)
    {
        if(prmDisponibilidad == null) return null;

        DTODisponibilidadRespuesta disponibilidadReturn = new DTODisponibilidadRespuesta();

        disponibilidadReturn.setDisponible(prmDisponibilidad.isDisponible());
        disponibilidadReturn.setStock(prmDisponibilidad.getStock());

        return disponibilidadReturn;
    }

    public List<DTODisponibilidadRespuesta> mapearDeListaDisponibilidadAListaRespuesta(List<Disponibilidad> prmDisponibilidades)
    {
        if(prmDisponibilidades == null) return null;

        List<DTODisponibilidadRespuesta> disponibilidadesReturn = new ArrayList<>();

        for(Disponibilidad disponibilidad : prmDisponibilidades)
        {
            disponibilidadesReturn.add(this.mapearDisponibilidadRespuesta(disponibilidad));
        }

        return disponibilidadesReturn;
    }

}
