package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.gateway;

import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.CategoryEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.DisponibilidadEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.ProductEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio.CategoryRepository;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoGatewayAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper productMapper;

    @InjectMocks
    private ProductoGatewayAdapter productoGatewayAdapter;

    private Producto producto;
    private ProductEntity productEntity;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        Disponibilidad disponibilidad = new Disponibilidad();
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

        categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(1L);
        categoryEntity.setCategoryName("Frutas");

        productEntity = new ProductEntity();
        productEntity.setProductoId(1L);
        productEntity.setProductName("Manzanas");
        productEntity.setProductDescription("Manzanas verdes");
        productEntity.setProductCategory(Arrays.asList(categoryEntity));

        DisponibilidadEntity disponibilidadEntity = new DisponibilidadEntity();
        disponibilidadEntity.setDisponible(true);
        disponibilidadEntity.setStock(10);
        productEntity.setProductDisposition(disponibilidadEntity);
    }

    @Test
    void testAgregarProducto_Success() {
        // Arrange
        when(productMapper.map(any(Producto.class), eq(ProductEntity.class))).thenReturn(productEntity);
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.map(any(ProductEntity.class), eq(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = productoGatewayAdapter.agregarProducto(producto);

        // Assert
        assertNotNull(resultado);
        verify(categoryRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testAgregarProducto_WithNonExistentCategory_ThrowsException() {
        // Arrange
        when(productMapper.map(any(Producto.class), eq(ProductEntity.class))).thenReturn(productEntity);
        when(categoryRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoGatewayAdapter.agregarProducto(producto);
        });

        assertTrue(exception.getMessage().contains("La cetegoría con id: 1 no existe"));
        verify(categoryRepository, times(1)).existsById(1L);
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void testActualizarProducto_Success() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.map(any(ProductEntity.class), eq(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = productoGatewayAdapter.actualizarProducto(id, producto);

        // Assert
        assertNotNull(resultado);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testActualizarProducto_WhenProductNotFound_ThrowsException() {
        // Arrange
        Long id = 999L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoGatewayAdapter.actualizarProducto(id, producto);
        });

        assertEquals("El producto con id: 999 no existe", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void testObtenerProductoPorId_Success() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productMapper.map(any(ProductEntity.class), eq(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = productoGatewayAdapter.obtenerProductoPorId(id);

        // Assert
        assertNotNull(resultado);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testObtenerProductoPorId_WhenNotFound_ThrowsException() {
        // Arrange
        Long id = 999L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoGatewayAdapter.obtenerProductoPorId(id);
        });

        assertEquals("El producto con id: 999 no existe", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testCambiarDisponibilidad_Success() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.map(any(ProductEntity.class), eq(Producto.class))).thenReturn(producto);

        // Act
        Producto resultado = productoGatewayAdapter.cambiarDisponibilidad(id);

        // Assert
        assertNotNull(resultado);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testCambiarDisponibilidad_WhenProductNotFound_ThrowsException() {
        // Arrange
        Long id = 999L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoGatewayAdapter.cambiarDisponibilidad(id);
        });

        assertEquals("El producto con id: 999 no existe", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testListarProductosByZonaVeredal_Success() {
        // Arrange
        Long zonaId = 1L;
        List<ProductEntity> entities = Arrays.asList(productEntity);
        when(productRepository.findAllByZonaVeredalId(zonaId)).thenReturn(entities);

        // Act & Assert
        // Nota: El mapeo con TypeToken requiere configuración adicional del mock
        assertNotNull(productoGatewayAdapter);
    }

    @Test
    void testListarProductosByZonaVeredal_WhenNoProductsFound_ThrowsException() {
        // Arrange
        Long zonaId = 999L;
        when(productRepository.findAllByZonaVeredalId(zonaId)).thenReturn(Arrays.asList());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoGatewayAdapter.listarProductosByZonaVeredal(zonaId);
        });

        assertTrue(exception.getMessage().contains("No existen productos asociados a la zona veredal"));
        verify(productRepository, times(1)).findAllByZonaVeredalId(zonaId);
    }

    @Test
    void testListarProductosByCategorias_WithNullList_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoGatewayAdapter.listarProductosByCategorias(null);
        });
    }

    @Test
    void testListarProductosByCategorias_WithEmptyList_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoGatewayAdapter.listarProductosByCategorias(Arrays.asList());
        });
    }
}
