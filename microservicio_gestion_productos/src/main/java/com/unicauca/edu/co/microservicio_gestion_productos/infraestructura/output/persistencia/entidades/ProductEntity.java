package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un producto en la BD
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    @Id @Column(name = "productoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;/**<!Identificador único del producto */

    private String productName;/**Nombre del producto*/

    @ManyToMany
    @JoinTable(name = "product_category",
        joinColumns = @jakarta.persistence.JoinColumn(name = "productoId"),
        inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "categoryId"))
    private List<CategoryEntity> productCategory;/**Uso del agregado*/

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private DisponibilidadEntity productDisposition;/**Uso del objeto de valor*/
    
    private String productDescription;/**Descripcion del producto */
}
