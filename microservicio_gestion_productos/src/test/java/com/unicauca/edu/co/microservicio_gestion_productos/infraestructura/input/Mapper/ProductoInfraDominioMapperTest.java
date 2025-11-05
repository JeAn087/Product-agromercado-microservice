package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOCategoriaPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTODisponibilidadPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOProductoPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOProductoRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoInfraDominioMapperTest {

    private ProductoInfraDominioMapper mapper;
    private DTOProductoPeticion dtoPeticion;
    private Producto producto;

    @BeforeEach
    void setUp() {
        mapper = new ProductoInfraDominioMapper();

        DTOCategoriaPeticion categoriaPeticion = new DTOCategoriaPeticion(1L, "Frutas", "Productos frutales");
        DTODisponibilidadPeticion disponibilidadPeticion = new DTODisponibilidadPeticion(true, 10);

        dtoPeticion = new DTOProductoPeticion(1L, "Manzanas", Arrays.asList(categoriaPeticion), disponibilidadPeticion, "Manzanas verdes");

        Disponibilidad disponibilidad = new Disponibilidad();
        disponibilidad.setDisponible(true);
        disponibilidad.setStock(10);

        Categoria categoria = new Categoria();
        categoria.setCategoryId(1L);
        categoria.setCategoryName("Frutas");
        categoria.setCategoryDescription("Productos frutales");

        producto = new Producto();
        producto.setProductoId(1L);
        producto.setProductName("Manzanas");
        producto.setProductDescription("Manzanas verdes");
        producto.setProductCategory(Arrays.asList(categoria));
        producto.setProductDisposition(disponibilidad);
    }

    @Test
    void testMapearDePeticionAProducto_Success() {
        // Act
        Producto resultado = mapper.mapearDePeticionAProducto(dtoPeticion);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getProductoId());
        assertEquals("Manzanas", resultado.getProductName());
        assertEquals("Manzanas verdes", resultado.getProductDescription());
        assertNotNull(resultado.getProductCategory());
        assertEquals(1, resultado.getProductCategory().size());
        assertNotNull(resultado.getProductDisposition());
        assertEquals(10, resultado.getProductDisposition().getStock());
    }

    @Test
    void testMapearDePeticionAProducto_WithNull_ReturnsNull() {
        // Act
        Producto resultado = mapper.mapearDePeticionAProducto(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDePeticionAProducto_WithNullCategories() {
        // Arrange
        DTOProductoPeticion dtoSinCategorias = new DTOProductoPeticion(1L, "Manzanas", null, null, "Manzanas verdes");

        // Act
        Producto resultado = mapper.mapearDePeticionAProducto(dtoSinCategorias);

        // Assert
        assertNotNull(resultado);
        assertEquals("Manzanas", resultado.getProductName());
    }

    @Test
    void testMapearDeProductoARespuesta_Success() {
        // Act
        DTOProductoRespuesta resultado = mapper.mapearDeProductoARespuesta(producto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getProductoId());
        assertEquals("Manzanas", resultado.getProductName());
        assertEquals("Manzanas verdes", resultado.getProductDescription());
    }

    @Test
    void testMapearDeProductoARespuesta_WithNull_ReturnsNull() {
        // Act
        DTOProductoRespuesta resultado = mapper.mapearDeProductoARespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeListaProductoAListaRespuesta_Success() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);

        // Act
        List<DTOProductoRespuesta> resultado = mapper.mapearDeListaProductoAListaRespuesta(productos);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getProductoId());
    }

    @Test
    void testMapearDeListaProductoAListaRespuesta_WithNull_ReturnsNull() {
        // Act
        List<DTOProductoRespuesta> resultado = mapper.mapearDeListaProductoAListaRespuesta(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void testMapearDeListaProductoAListaRespuesta_WithEmptyList() {
        // Arrange
        List<Producto> productos = Arrays.asList();

        // Act
        List<DTOProductoRespuesta> resultado = mapper.mapearDeListaProductoAListaRespuesta(productos);

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }
}
