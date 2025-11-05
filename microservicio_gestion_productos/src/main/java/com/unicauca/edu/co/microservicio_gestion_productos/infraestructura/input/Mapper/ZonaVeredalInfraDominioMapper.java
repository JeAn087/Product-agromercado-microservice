package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.ZonaVeredal;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOZonaVeredalRespuesta;

public class ZonaVeredalInfraDominioMapper {
    public DTOZonaVeredalRespuesta mapearDeZonaARespuesta(ZonaVeredal prmZona)
    {
        if(prmZona == null) return null;

        DTOZonaVeredalRespuesta zonaReturn = new DTOZonaVeredalRespuesta();

        zonaReturn.setZonaId(prmZona.getZonaId());
        zonaReturn.setNombre(prmZona.getNombre());
        zonaReturn.setUbicacion(prmZona.getUbicacion());
        zonaReturn.setDescripcion(prmZona.getDescripcion());
        return zonaReturn;
    }

    public List<DTOZonaVeredalRespuesta> mapearDeListaProductoAListaRespuesta(List<ZonaVeredal> prmListaZonas)
    {
        if(prmListaZonas == null) return null;

        List<DTOZonaVeredalRespuesta> listaReturn = new ArrayList<>();
        for (ZonaVeredal zona : prmListaZonas) {
            listaReturn.add(this.mapearDeZonaARespuesta(zona));
        }

        return listaReturn;
    }
}
