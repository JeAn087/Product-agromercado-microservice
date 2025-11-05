package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTODisponibilidadPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTODisponibilidadRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadInfraDominioMapperTest {

    private DisponibilidadInfraDominioMapper mapper;
    private DTODisponibilidadPeticion dtoPeticion;
    private Disponibilidad disponibilidad;

    @BeforeEach
    void setUp() {
        mapper = new DisponibilidadInfraDominioMapper();

        dtoPeticion = new DTODisponibilidadPeticion(true, 10);

        disponibilidad = new Disponibilidad();
        disponibilidad.setDisponible(true);
        disponibilidad.setStock(10);
    }

    @Test
    void testMapearDePeticionADisponibilidad_Success() {
        // Act
        Disponibilidad resultado = mapper.mapearDePeticionADisponibilidad(dtoPeticion);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isDisponible());
        assertEquals(10, resultado.getStock());
    }

    @Test
    void testMapearDePeticionADisponibilidad_WithNull_ReturnsNull() {
        // Act
        Disponibilidad resultado = mapper.mapearDePeticionADisponibilidad(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDisponibilidadRespuesta_Success() {
        // Act
        DTODisponibilidadRespuesta resultado = mapper.mapearDisponibilidadRespuesta(disponibilidad);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isDisponible());
        assertEquals(10, resultado.getStock());
    }

    @Test
    void testMapearDisponibilidadRespuesta_WithNull_ReturnsNull() {
        // Act
        DTODisponibilidadRespuesta resultado = mapper.mapearDisponibilidadRespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDisponibilidadRespuesta_WithFalseDisponible() {
        // Arrange
        disponibilidad.setDisponible(false);

        // Act
        DTODisponibilidadRespuesta resultado = mapper.mapearDisponibilidadRespuesta(disponibilidad);

        // Assert
        assertNotNull(resultado);
        assertFalse(resultado.isDisponible());
        assertEquals(10, resultado.getStock());
    }

    @Test
    void testMapearDeListaDisponibilidadAListaRespuesta_Success() {
        // Arrange
        List<Disponibilidad> disponibilidades = Arrays.asList(disponibilidad);

        // Act
        List<DTODisponibilidadRespuesta> resultado = mapper.mapearDeListaDisponibilidadAListaRespuesta(disponibilidades);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).isDisponible());
        assertEquals(10, resultado.get(0).getStock());
    }

    @Test
    void testMapearDeListaDisponibilidadAListaRespuesta_WithNull_ReturnsNull() {
        // Act
        List<DTODisponibilidadRespuesta> resultado = mapper.mapearDeListaDisponibilidadAListaRespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeListaDisponibilidadAListaRespuesta_WithEmptyList() {
        // Arrange
        List<Disponibilidad> disponibilidades = Arrays.asList();

        // Act
        List<DTODisponibilidadRespuesta> resultado = mapper.mapearDeListaDisponibilidadAListaRespuesta(disponibilidades);

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }
}
