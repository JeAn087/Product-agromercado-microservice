package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionCategoriasPort;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOCategoriaPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOCategoriaRespuesta;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper.CategoriaInfraDominioMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final GestionCategoriasPort categoriaService;
    private final CategoriaInfraDominioMapper categoriaMapper;

    public CategoriaController(@Qualifier("crearGestorCategorias") GestionCategoriasPort categoriaService, CategoriaInfraDominioMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @PostMapping
    public ResponseEntity<DTOCategoriaRespuesta> agregarCategoria(@RequestBody DTOCategoriaPeticion bodyPeticion) {
        Categoria objCategoriaMapeada = categoriaMapper.mapearDePeticionACategoria(bodyPeticion);
        Categoria creada = categoriaService.agregarCategoria(objCategoriaMapeada);
        ResponseEntity<DTOCategoriaRespuesta> respuesta = new ResponseEntity<>(
            categoriaMapper.mapearDeCategoriaARespuesta(creada),
            HttpStatus.CREATED
        );
        return respuesta;
    }

    @GetMapping("/listCategorias")
    public ResponseEntity<List<DTOCategoriaRespuesta>> listarCategorias() {
        List<Categoria> categoriasEncontradas = categoriaService.listarCategorias();
        ResponseEntity<List<DTOCategoriaRespuesta>> respuesta = new ResponseEntity<>(
            categoriaMapper.mapearDeListaCategoriaAListaRespuesta(categoriasEncontradas),
            HttpStatus.OK
        );
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOCategoriaRespuesta> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoriaEncontrada = categoriaService.obtenerCategoriaPorId(id);
        ResponseEntity<DTOCategoriaRespuesta> respuesta = new ResponseEntity<>(
            categoriaMapper.mapearDeCategoriaARespuesta(categoriaEncontrada),
            HttpStatus.OK
        );
        return respuesta;
    }
}