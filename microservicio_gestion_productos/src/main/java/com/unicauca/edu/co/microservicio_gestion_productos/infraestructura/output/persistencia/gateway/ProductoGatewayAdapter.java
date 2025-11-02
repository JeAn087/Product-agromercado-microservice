package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionProductosGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Producto;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.CategoryEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.DisponibilidadEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.ProductEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio.CategoryRepository;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio.ProductRepository;

@Service
public class ProductoGatewayAdapter implements GestionProductosGateway {

    private final ProductRepository BDProductoRegister;
    private final CategoryRepository BDCategoryRegister;
    private final ModelMapper productMapper;

    public ProductoGatewayAdapter(ProductRepository bDProductoRegister, CategoryRepository bDCategoryRegister,
            ModelMapper productMapper) {
        BDProductoRegister = bDProductoRegister;
        BDCategoryRegister = bDCategoryRegister;
        this.productMapper = productMapper;
    }

    @Override
    public Producto agregarProducto(Producto prmNuevoProducto) {
        ProductEntity objProductoMapeado = productMapper.map(prmNuevoProducto, ProductEntity.class);
        
        List<CategoryEntity> categoriasProductoNuevo = objProductoMapeado.getProductCategory();
        
        for (CategoryEntity objCategory : categoriasProductoNuevo) {
            if(!BDCategoryRegister.existsById(objCategory.getCategoryId()))
            {
                System.out.println("la categoria con id: " + objCategory.getCategoryId() + " no existe, no se crea");
                throw new RuntimeException("La cetegoría con id: "+objCategory.getCategoryId()+" no existe");
            }
        }
        
        DisponibilidadEntity objDisponibilidad = new DisponibilidadEntity();
        objDisponibilidad.setDisponible(true);
        objDisponibilidad.setStock(1);
        objDisponibilidad.setProducto(objProductoMapeado);//asociar llave del producto
        objProductoMapeado.setProductDisposition(objDisponibilidad);
        ProductEntity productoGuardado = BDProductoRegister.save(objProductoMapeado);
        Producto productoRetornado = productMapper.map(productoGuardado, Producto.class);
        return productoRetornado;
    }

    @Override
    public Producto actualizarProducto(Long prmIdProducto, Producto prmProductoActualizado) {
          //Verificar existencia
    ProductEntity productoExistente = BDProductoRegister.findById(prmIdProducto)
        .orElseThrow(() -> new RuntimeException("El producto con id: " + prmIdProducto + " no existe"));

        productMapper.map(prmProductoActualizado, productoExistente);//Mapear el producto actualizado en la variable de producto existente
        ProductEntity productoGuardado = BDProductoRegister.save(productoExistente);
        Producto productoRetornado = productMapper.map(productoGuardado, Producto.class);
        return productoRetornado;
    }

    @Override
    public Producto obtenerProductoPorId(Long prmIdProducto) {
        ProductEntity productoBDById = BDProductoRegister.findById(prmIdProducto)
            .orElseThrow(() -> new RuntimeException("El producto con id: " + prmIdProducto + " no existe"));
        Producto productoRetornado = productMapper.map(productoBDById, Producto.class);
        return productoRetornado;
    }

    @Override
    public List<Producto> listarProductos() {
        Iterable<ProductEntity> productosListBD = BDProductoRegister.findAll();
        List<Producto> productosReturnList = productMapper.map(productosListBD, new TypeToken<List<Producto>>(){}.getType());
        return productosReturnList;
    }

    @Override
    public Producto cambiarDisponibilidad(Long prmIdProducto) {
             //Verificar existencia
    ProductEntity productoExistente = BDProductoRegister.findById(prmIdProducto)
        .orElseThrow(() -> new RuntimeException("El producto con id: " + prmIdProducto + " no existe"));
        boolean disponibilidad = productoExistente.getProductDisposition().isDisponible();
        productoExistente.getProductDisposition().setDisponible(!disponibilidad);
        ProductEntity productoGuardado = BDProductoRegister.save(productoExistente);
        Producto productoRetornado = productMapper.map(productoGuardado, Producto.class);
        return productoRetornado;
    }
}