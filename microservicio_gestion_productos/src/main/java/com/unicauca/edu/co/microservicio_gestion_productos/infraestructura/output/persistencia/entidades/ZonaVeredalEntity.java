package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades;

import java.util.List;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "zonaVeredal")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ZonaVeredalEntity {
    @Id @Column(name = "zonaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zonaId;

    private String nombre;

    private String descripcion;

    private String ubicacion;

    @ManyToMany(mappedBy = "productZones")
    private List<ProductEntity> zone_products;
}
