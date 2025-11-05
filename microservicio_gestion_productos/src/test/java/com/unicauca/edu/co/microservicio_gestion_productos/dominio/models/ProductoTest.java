package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;
    private Disponibilidad disponibilidad;

    @BeforeEach
    void setUp() {
        disponibilidad = new Disponibilidad();
        disponibilidad.setDisponible(true);
        disponibilidad.setStock(10);

        Categoria categoria = new Categoria();
        categoria.setCategoryId(1L);
        categoria.setCategoryName("Frutas");

        producto = new Producto();
        producto.setProductoId(1L);
        producto.setProductName("Manzanas");
        producto.setProductDescription("Manzanas verdes");
        producto.setProductDisposition(disponibilidad);
        producto.setProductCategory(Arrays.asList(categoria));
    }

    @Test
    void testVerificarStock_WithPositiveStock_ReturnsTrue() {
        // Arrange
        disponibilidad.setStock(10);

        // Act
        boolean resultado = producto.verificarStock();

        // Assert
        assertTrue(resultado);
    }

    @Test
    void testVerificarStock_WithStockGreaterThanZero_ReturnsTrue() {
        // Arrange
        disponibilidad.setStock(1);

        // Act
        boolean resultado = producto.verificarStock();

        // Assert
        assertTrue(resultado);
    }

    @Test
    void testVerificarStock_WithZeroStock_ThrowsException() {
        // Arrange
        disponibilidad.setStock(0);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            producto.verificarStock();
        });

        assertEquals("El stock del producto debe ser positivo y mayor a cero", exception.getMessage());
    }

    @Test
    void testVerificarStock_WithNegativeStock_ThrowsException() {
        // Arrange
        disponibilidad.setStock(-5);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            producto.verificarStock();
        });

        assertEquals("El stock del producto debe ser positivo y mayor a cero", exception.getMessage());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Producto nuevoProducto = new Producto();
        Disponibilidad nuevaDisponibilidad = new Disponibilidad();
        nuevaDisponibilidad.setStock(5);
        nuevaDisponibilidad.setDisponible(true);

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setCategoryId(2L);
        nuevaCategoria.setCategoryName("Verduras");

        // Act
        nuevoProducto.setProductoId(2L);
        nuevoProducto.setProductName("Lechugas");
        nuevoProducto.setProductDescription("Lechugas frescas");
        nuevoProducto.setProductDisposition(nuevaDisponibilidad);
        nuevoProducto.setProductCategory(Arrays.asList(nuevaCategoria));

        // Assert
        assertEquals(2L, nuevoProducto.getProductoId());
        assertEquals("Lechugas", nuevoProducto.getProductName());
        assertEquals("Lechugas frescas", nuevoProducto.getProductDescription());
        assertNotNull(nuevoProducto.getProductDisposition());
        assertNotNull(nuevoProducto.getProductCategory());
        assertEquals(1, nuevoProducto.getProductCategory().size());
    }
}
