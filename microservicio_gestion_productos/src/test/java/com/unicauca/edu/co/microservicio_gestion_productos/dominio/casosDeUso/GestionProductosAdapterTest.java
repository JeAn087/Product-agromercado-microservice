package com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionProductosGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GestionProductosAdapterTest {

    @Mock
    private GestionProductosGateway gatewayProductos;

    @InjectMocks
    private GestionProductosAdapter gestionProductosAdapter;

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
    void testAgregarProducto_Success() {
        // Arrange
        when(gatewayProductos.agregarProducto(any(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = gestionProductosAdapter.agregarProducto(producto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getProductoId());
        assertEquals("Manzanas", resultado.getProductName());
        verify(gatewayProductos, times(1)).agregarProducto(any(Producto.class));
    }

    @Test
    void testActualizarProducto_Success_WithValidStock() {
        // Arrange
        Long id = 1L;
        when(gatewayProductos.actualizarProducto(eq(id), any(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = gestionProductosAdapter.actualizarProducto(id, producto);

        // Assert
        assertNotNull(resultado);
        verify(gatewayProductos, times(1)).actualizarProducto(eq(id), any(Producto.class));
    }

    @Test
    void testActualizarProducto_WithInvalidStock_ReturnsNull() {
        // Arrange
        Long id = 1L;
        Disponibilidad stockNegativo = new Disponibilidad();
        stockNegativo.setStock(-1);
        stockNegativo.setDisponible(true);
        producto.setProductDisposition(stockNegativo);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            producto.verificarStock();
        });
    }

    @Test
    void testObtenerProductoPorId_Success() {
        // Arrange
        Long id = 1L;
        when(gatewayProductos.obtenerProductoPorId(id)).thenReturn(producto);

        // Act
        Producto resultado = gestionProductosAdapter.obtenerProductoPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getProductoId());
        verify(gatewayProductos, times(1)).obtenerProductoPorId(id);
    }

    @Test
    void testListarProductos_Success() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        when(gatewayProductos.listarProductos()).thenReturn(productos);

        // Act
        List<Producto> resultado = gestionProductosAdapter.listarProductos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gatewayProductos, times(1)).listarProductos();
    }

    @Test
    void testCambiarDisponibilidad_Success() {
        // Arrange
        Long id = 1L;
        Producto productoActualizado = new Producto();
        productoActualizado.setProductoId(1L);
        Disponibilidad nuevaDisponibilidad = new Disponibilidad();
        nuevaDisponibilidad.setDisponible(false);
        nuevaDisponibilidad.setStock(10);
        productoActualizado.setProductDisposition(nuevaDisponibilidad);

        when(gatewayProductos.cambiarDisponibilidad(id)).thenReturn(productoActualizado);

        // Act
        Producto resultado = gestionProductosAdapter.cambiarDisponibilidad(id);

        // Assert
        assertNotNull(resultado);
        verify(gatewayProductos, times(1)).cambiarDisponibilidad(id);
    }

    @Test
    void testListarProductosByZonaVeredal_Success() {
        // Arrange
        Long zonaId = 1L;
        List<Producto> productos = Arrays.asList(producto);
        when(gatewayProductos.listarProductosByZonaVeredal(zonaId)).thenReturn(productos);

        // Act
        List<Producto> resultado = gestionProductosAdapter.listarProductosByZonaVeredal(zonaId);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gatewayProductos, times(1)).listarProductosByZonaVeredal(zonaId);
    }

    @Test
    void testListarProductosByCategorias_Success() {
        // Arrange
        List<Long> categoriaIds = Arrays.asList(1L, 2L);
        List<Producto> productos = Arrays.asList(producto);
        when(gatewayProductos.listarProductosByCategorias(categoriaIds)).thenReturn(productos);

        // Act
        List<Producto> resultado = gestionProductosAdapter.listarProductosByCategorias(categoriaIds);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gatewayProductos, times(1)).listarProductosByCategorias(categoriaIds);
    }
}
