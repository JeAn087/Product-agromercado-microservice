package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
