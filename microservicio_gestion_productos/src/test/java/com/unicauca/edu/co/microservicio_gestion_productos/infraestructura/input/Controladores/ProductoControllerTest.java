package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionProductosPort;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Disponibilidad;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOProductoPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOProductoRespuesta;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper.ProductoInfraDominioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private GestionProductosPort productoService;

    @Mock
    private ProductoInfraDominioMapper productoMapper;

    @InjectMocks
    private ProductoController productoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Producto producto;
    private DTOProductoPeticion dtoPeticion;
    private DTOProductoRespuesta dtoRespuesta;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        objectMapper = new ObjectMapper();

        // Configurar datos de prueba
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
        producto.setProductDisposition(disponibilidad);
        producto.setProductCategory(Arrays.asList(categoria));

        dtoPeticion = new DTOProductoPeticion(1L, "Manzanas", null, null, "Manzanas verdes");

        dtoRespuesta = new DTOProductoRespuesta(1L, "Manzanas", null, null, "Manzanas verdes", null);
    }

    @Test
    void testAgregarProducto_Success() throws Exception {
        // Arrange
        when(productoMapper.mapearDePeticionAProducto(any(DTOProductoPeticion.class))).thenReturn(producto);
        when(productoService.agregarProducto(any(Producto.class))).thenReturn(producto);
        when(productoMapper.mapearDeProductoARespuesta(any(Producto.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(post("/api/producto/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoPeticion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(1L))
                .andExpect(jsonPath("$.productName").value("Manzanas"));

        verify(productoMapper, times(1)).mapearDePeticionAProducto(any(DTOProductoPeticion.class));
        verify(productoService, times(1)).agregarProducto(any(Producto.class));
        verify(productoMapper, times(1)).mapearDeProductoARespuesta(any(Producto.class));
    }

    @Test
    void testActualizarProducto_Success() throws Exception {
        // Arrange
        Long id = 1L;
        when(productoMapper.mapearDePeticionAProducto(any(DTOProductoPeticion.class))).thenReturn(producto);
        when(productoService.actualizarProducto(eq(id), any(Producto.class))).thenReturn(producto);
        when(productoMapper.mapearDeProductoARespuesta(any(Producto.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(put("/api/producto/actualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoPeticion)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.productoId").value(1L));

        verify(productoService, times(1)).actualizarProducto(eq(id), any(Producto.class));
    }

    @Test
    void testCambiarDisponibilidad_Success() throws Exception {
        // Arrange
        Long id = 1L;
        Producto productoActualizado = new Producto();
        productoActualizado.setProductoId(1L);
        Disponibilidad nuevaDisponibilidad = new Disponibilidad();
        nuevaDisponibilidad.setDisponible(false);
        nuevaDisponibilidad.setStock(10);
        productoActualizado.setProductDisposition(nuevaDisponibilidad);

        when(productoService.cambiarDisponibilidad(id)).thenReturn(productoActualizado);
        when(productoMapper.mapearDeProductoARespuesta(any(Producto.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(patch("/api/producto/cambiarDisponibilidad/{id}", id))
                .andExpect(status().isAccepted());

        verify(productoService, times(1)).cambiarDisponibilidad(id);
    }

    @Test
    void testListarProductos_Success() throws Exception {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        List<DTOProductoRespuesta> dtos = Arrays.asList(dtoRespuesta);

        when(productoService.listarProductos()).thenReturn(productos);
        when(productoMapper.mapearDeListaProductoAListaRespuesta(productos)).thenReturn(dtos);

        // Act & Assert
        mockMvc.perform(get("/api/producto/consultar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].productoId").value(1L));

        verify(productoService, times(1)).listarProductos();
    }

    @Test
    void testObtenerProductoPorId_Success() throws Exception {
        // Arrange
        Long id = 1L;
        when(productoService.obtenerProductoPorId(id)).thenReturn(producto);
        when(productoMapper.mapearDeProductoARespuesta(any(Producto.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(get("/api/producto/consultar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(1L));

        verify(productoService, times(1)).obtenerProductoPorId(id);
    }

    @Test
    void testObtenerProductosPorZona_Success() throws Exception {
        // Arrange
        Long zonaId = 1L;
        List<Producto> productos = Arrays.asList(producto);
        List<DTOProductoRespuesta> dtos = Arrays.asList(dtoRespuesta);

        when(productoService.listarProductosByZonaVeredal(zonaId)).thenReturn(productos);
        when(productoMapper.mapearDeListaProductoAListaRespuesta(productos)).thenReturn(dtos);

        // Act & Assert
        mockMvc.perform(get("/api/producto/consultar/porZona/{id}", zonaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(productoService, times(1)).listarProductosByZonaVeredal(zonaId);
    }

    @Test
    void testObtenerProductosPorCategoria_Success() throws Exception {
        // Arrange
        List<Long> categoriaIds = Arrays.asList(1L, 2L);
        List<Producto> productos = Arrays.asList(producto);
        List<DTOProductoRespuesta> dtos = Arrays.asList(dtoRespuesta);

        when(productoService.listarProductosByCategorias(categoriaIds)).thenReturn(productos);
        when(productoMapper.mapearDeListaProductoAListaRespuesta(productos)).thenReturn(dtos);

        // Act & Assert
        mockMvc.perform(get("/api/producto/consultar/porCategoria")
                .param("ids", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(productoService, times(1)).listarProductosByCategorias(categoriaIds);
    }

    @Test
    void testListarProductos_EmptyList() throws Exception {
        // Arrange
        when(productoService.listarProductos()).thenReturn(new ArrayList<>());
        when(productoMapper.mapearDeListaProductoAListaRespuesta(anyList())).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(get("/api/producto/consultar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(productoService, times(1)).listarProductos();
    }
}
