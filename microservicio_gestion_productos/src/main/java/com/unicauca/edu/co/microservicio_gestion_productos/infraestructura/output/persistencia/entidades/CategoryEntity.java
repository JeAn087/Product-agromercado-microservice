package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades;

import java.util.List;

import org.springframework.boot.context.properties.bind.Name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una caetegoría en la BD
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoryEntity {
    @Id @Name("categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;/**Identificador único de la categoría */

    private String categoryName;/**Nombre Único de la categoría */
    
    private String categoryDescription;/**Descripción breve de la categoría*/

    @ManyToMany(mappedBy = "productCategory")
    private List<ProductEntity> products; 
}
