package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionCategoriasPort;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOCategoriaPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOCategoriaRespuesta;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper.CategoriaInfraDominioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private GestionCategoriasPort categoriaService;

    @Mock
    private CategoriaInfraDominioMapper categoriaMapper;

    @InjectMocks
    private CategoriaController categoriaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Categoria categoria;
    private DTOCategoriaPeticion dtoPeticion;
    private DTOCategoriaRespuesta dtoRespuesta;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
        objectMapper = new ObjectMapper();

        categoria = new Categoria();
        categoria.setCategoryId(1L);
        categoria.setCategoryName("Frutas");
        categoria.setCategoryDescription("Productos frutales");

        dtoPeticion = new DTOCategoriaPeticion(1L, "Frutas", "Productos frutales");

        dtoRespuesta = new DTOCategoriaRespuesta(1L, "Frutas", "Productos frutales");
    }

    @Test
    void testAgregarCategoria_Success() throws Exception {
        // Arrange
        when(categoriaMapper.mapearDePeticionACategoria(any(DTOCategoriaPeticion.class))).thenReturn(categoria);
        when(categoriaService.agregarCategoria(any(Categoria.class))).thenReturn(categoria);
        when(categoriaMapper.mapearDeCategoriaARespuesta(any(Categoria.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(post("/api/categoria/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoPeticion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.categoryName").value("Frutas"));

        verify(categoriaMapper, times(1)).mapearDePeticionACategoria(any(DTOCategoriaPeticion.class));
        verify(categoriaService, times(1)).agregarCategoria(any(Categoria.class));
        verify(categoriaMapper, times(1)).mapearDeCategoriaARespuesta(any(Categoria.class));
    }

    @Test
    void testListarCategorias_Success() throws Exception {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria);
        List<DTOCategoriaRespuesta> dtos = Arrays.asList(dtoRespuesta);

        when(categoriaService.listarCategorias()).thenReturn(categorias);
        when(categoriaMapper.mapearDeListaCategoriaAListaRespuesta(categorias)).thenReturn(dtos);

        // Act & Assert
        mockMvc.perform(get("/api/categoria/consultar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].categoryName").value("Frutas"));

        verify(categoriaService, times(1)).listarCategorias();
    }

    @Test
    void testObtenerCategoriaPorId_Success() throws Exception {
        // Arrange
        Long id = 1L;
        when(categoriaService.obtenerCategoriaPorId(id)).thenReturn(categoria);
        when(categoriaMapper.mapearDeCategoriaARespuesta(any(Categoria.class))).thenReturn(dtoRespuesta);

        // Act & Assert
        mockMvc.perform(get("/api/categoria/consultar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.categoryName").value("Frutas"));

        verify(categoriaService, times(1)).obtenerCategoriaPorId(id);
    }

    @Test
    void testListarCategorias_EmptyList() throws Exception {
        // Arrange
        when(categoriaService.listarCategorias()).thenReturn(new ArrayList<>());
        when(categoriaMapper.mapearDeListaCategoriaAListaRespuesta(anyList())).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(get("/api/categoria/consultar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(categoriaService, times(1)).listarCategorias();
    }
}
