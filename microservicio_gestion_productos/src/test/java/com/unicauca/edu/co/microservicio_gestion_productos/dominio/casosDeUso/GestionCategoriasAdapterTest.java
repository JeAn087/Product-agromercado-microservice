package com.unicauca.edu.co.microservicio_gestion_productos.dominio.casosDeUso;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionCategoriaGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
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
class GestionCategoriasAdapterTest {

    @Mock
    private GestionCategoriaGateway gatewayCategorias;

    @InjectMocks
    private GestionCategoriasAdapter gestionCategoriasAdapter;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setCategoryId(1L);
        categoria.setCategoryName("Frutas");
        categoria.setCategoryDescription("Productos frutales");
    }

    @Test
    void testAgregarCategoria_Success() {
        // Arrange
        when(gatewayCategorias.agregarCategoria(any(Categoria.class))).thenReturn(categoria);

        // Act
        Categoria resultado = gestionCategoriasAdapter.agregarCategoria(categoria);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoryId());
        assertEquals("Frutas", resultado.getCategoryName());
        verify(gatewayCategorias, times(1)).agregarCategoria(any(Categoria.class));
    }

    @Test
    void testActualizarCategoria_Success() {
        // Arrange
        Long id = 1L;
        when(gatewayCategorias.actualizarCategoria(eq(id), any(Categoria.class))).thenReturn(categoria);

        // Act
        Categoria resultado = gestionCategoriasAdapter.actualizarCategoria(id, categoria);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoryId());
        verify(gatewayCategorias, times(1)).actualizarCategoria(eq(id), any(Categoria.class));
    }

    @Test
    void testListarCategorias_Success() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria);
        when(gatewayCategorias.listarCategorias()).thenReturn(categorias);

        // Act
        List<Categoria> resultado = gestionCategoriasAdapter.listarCategorias();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Frutas", resultado.get(0).getCategoryName());
        verify(gatewayCategorias, times(1)).listarCategorias();
    }

    @Test
    void testObtenerCategoriaPorId_Success() {
        // Arrange
        Long id = 1L;
        when(gatewayCategorias.obtenerCategoriaPorId(id)).thenReturn(categoria);

        // Act
        Categoria resultado = gestionCategoriasAdapter.obtenerCategoriaPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoryId());
        assertEquals("Frutas", resultado.getCategoryName());
        verify(gatewayCategorias, times(1)).obtenerCategoriaPorId(id);
    }

    @Test
    void testListarCategorias_EmptyList() {
        // Arrange
        when(gatewayCategorias.listarCategorias()).thenReturn(Arrays.asList());

        // Act
        List<Categoria> resultado = gestionCategoriasAdapter.listarCategorias();

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(gatewayCategorias, times(1)).listarCategorias();
    }
}
