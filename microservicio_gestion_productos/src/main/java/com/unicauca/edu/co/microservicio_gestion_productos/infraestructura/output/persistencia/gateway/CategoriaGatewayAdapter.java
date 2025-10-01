package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.edu.co.microservicio_gestion_productos.aplicacion.output.GestionCategoriaGateway;
import com.unicauca.edu.co.microservicio_gestion_productos.dominio.models.Categoria;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.CategoryEntity;
import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio.CategoryRepository;

@Service
public class CategoriaGatewayAdapter implements GestionCategoriaGateway{

    private final CategoryRepository BDCategoriaRegister;
    private final ModelMapper categoryMapper;

    public CategoriaGatewayAdapter(CategoryRepository bDCategoriaRegister, ModelMapper categoryMapper) {
        BDCategoriaRegister = bDCategoriaRegister;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Categoria agregarCategoria(Categoria prmNuevaCategoria) {
        CategoryEntity objCategoriaMapeada = null;//Crear instancia nula de una entidad
        objCategoriaMapeada = categoryMapper.map(prmNuevaCategoria, CategoryEntity.class);//Mapear la categoria que llega como parámetro a una entidad
        CategoryEntity categoriaGuardada = BDCategoriaRegister.save(objCategoriaMapeada);//Guardar la entidad en la BD
        Categoria categoriaRetornada = categoryMapper.map(categoriaGuardada, Categoria.class);//Mapear la entidad guardada a una respuesta
        return categoriaRetornada;
    }

    @Override
public Categoria actualizarCategoria(Long prmIdCategoria, Categoria prmCategoriaActualizada) {
    if (!BDCategoriaRegister.existsById(prmIdCategoria)) {
        throw new RuntimeException("La categoría con id: " + prmIdCategoria + " no existe");
    }

    // Mapear la categoría actualizada a entidad
    CategoryEntity objCategoriaMapeada = categoryMapper.map(prmCategoriaActualizada, CategoryEntity.class);
    CategoryEntity categoriaGuardada = BDCategoriaRegister.save(objCategoriaMapeada);
    Categoria categoriaRetornada = categoryMapper.map(categoriaGuardada, Categoria.class);
    return categoriaRetornada;
}

    @Override
    public List<Categoria> listarCategorias() {
        Iterable<CategoryEntity> categoriasListBD = BDCategoriaRegister.findAll();
        List<Categoria> categoriasReturnList = categoryMapper.map(categoriasListBD, new TypeToken<List<Categoria>>(){}.getType());
        return categoriasReturnList;
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long prmIdCategoria) {
        CategoryEntity categoriaBDById = BDCategoriaRegister.findById(prmIdCategoria).orElseThrow(() -> new RuntimeException("La categoría con id: " + prmIdCategoria + " no existe"));
        Categoria categoriaRetornada = categoryMapper.map(categoriaBDById, Categoria.class);
        return categoriaRetornada;
    }

}
