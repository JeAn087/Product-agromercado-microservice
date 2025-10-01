package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.input.GestionProductosPort;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTOPeticion.DTOProductoPeticion;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.DTORespuesta.DTOProductoRespuesta;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.input.Mapper.ProductoInfraDominioMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Controlador Rest Para la Gestión de Producos
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final GestionProductosPort productoService;

    private final ProductoInfraDominioMapper productoMapper;

    public ProductoController(@Qualifier("crearGestorProductos") GestionProductosPort productoService, ProductoInfraDominioMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @PostMapping
    public ResponseEntity<DTOProductoRespuesta> agregarProducto(@RequestBody DTOProductoPeticion bodyPeticion)
    {
        Producto objProductoMapeado = productoMapper.mapearDePeticionAProducto(bodyPeticion);
        Producto creado = productoService.agregarProducto(objProductoMapeado);
        ResponseEntity<DTOProductoRespuesta> respuesta = new ResponseEntity<DTOProductoRespuesta>(
            productoMapper.mapearDeProductoARespuesta(creado),
            HttpStatus.CREATED
        );
        return respuesta;
    }

    @GetMapping("/listProducts")
    public ResponseEntity<List<DTOProductoRespuesta>> listarProductos() {
        List<Producto> productosEncontrados = productoService.listarProductos();
        ResponseEntity<List<DTOProductoRespuesta>> respuesta = new ResponseEntity<>(
            productoMapper.mapearDeListaProductoAListaRespuesta(productosEncontrados),
            HttpStatus.OK
        );
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOProductoRespuesta> obtenerProductoPorId(@PathVariable Long id) {
        Producto productoEncontrado = productoService.obtenerProductoPorId(id);
        ResponseEntity<DTOProductoRespuesta> respuesta = new ResponseEntity<>(
            productoMapper.mapearDeProductoARespuesta(productoEncontrado),
            HttpStatus.OK
        );
        return respuesta;
    }
    

    
    
        
}
