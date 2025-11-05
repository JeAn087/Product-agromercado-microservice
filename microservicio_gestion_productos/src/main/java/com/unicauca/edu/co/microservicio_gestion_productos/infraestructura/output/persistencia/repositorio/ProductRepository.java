package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    /**
     * Lista todos los productos que pertenecen a una o varias categorías.
     * @param categoryIds Lista de IDs de las categorías
     * @return lista de productos asociados
     */
    @Query("SELECT DISTINCT p FROM ProductEntity p JOIN p.productCategory c WHERE c.categoryId IN :categoryIds")
    List<ProductEntity> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    /**
     * Lista todos los productos asociados a una zona veredal
     * @param zonaId
     * @return
     */
    @Query("SELECT DISTINCT p FROM ProductEntity p JOIN p.productZones z WHERE z.zonaId = :prmZonaId")
    List<ProductEntity> findAllByZonaVeredalId(@Param("prmZonaId") Long prmZonaId);


}
