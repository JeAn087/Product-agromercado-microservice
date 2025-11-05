package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOCategoriaPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOCategoriaRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaInfraDominioMapperTest {

    private CategoriaInfraDominioMapper mapper;
    private DTOCategoriaPeticion dtoPeticion;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        mapper = new CategoriaInfraDominioMapper();

        dtoPeticion = new DTOCategoriaPeticion(1L, "Frutas", "Productos frutales");

        categoria = new Categoria();
        categoria.setCategoryId(1L);
        categoria.setCategoryName("Frutas");
        categoria.setCategoryDescription("Productos frutales");
    }

    @Test
    void testMapearDePeticionACategoria_Success() {
        // Act
        Categoria resultado = mapper.mapearDePeticionACategoria(dtoPeticion);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoryId());
        assertEquals("Frutas", resultado.getCategoryName());
        assertEquals("Productos frutales", resultado.getCategoryDescription());
    }

    @Test
    void testMapearDePeticionACategoria_WithNull_ReturnsNull() {
        // Act
        Categoria resultado = mapper.mapearDePeticionACategoria(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeCategoriaARespuesta_Success() {
        // Act
        DTOCategoriaRespuesta resultado = mapper.mapearDeCategoriaARespuesta(categoria);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoryId());
        assertEquals("Frutas", resultado.getCategoryName());
        assertEquals("Productos frutales", resultado.getCategoryDescription());
    }

    @Test
    void testMapearDeCategoriaARespuesta_WithNull_ReturnsNull() {
        // Act
        DTOCategoriaRespuesta resultado = mapper.mapearDeCategoriaARespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeListaCategoriaAListaRespuesta_Success() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria);

        // Act
        List<DTOCategoriaRespuesta> resultado = mapper.mapearDeListaCategoriaAListaRespuesta(categorias);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getCategoryId());
        assertEquals("Frutas", resultado.get(0).getCategoryName());
    }

    @Test
    void testMapearDeListaCategoriaAListaRespuesta_WithNull_ReturnsNull() {
        // Act
        List<DTOCategoriaRespuesta> resultado = mapper.mapearDeListaCategoriaAListaRespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeListaCategoriaAListaRespuesta_WithEmptyList() {
        // Arrange
        List<Categoria> categorias = Arrays.asList();

        // Act
        List<DTOCategoriaRespuesta> resultado = mapper.mapearDeListaCategoriaAListaRespuesta(categorias);

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }
}
