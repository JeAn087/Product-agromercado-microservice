package com.unicauca.edu.co.microservicio_gestion_productos.infraestructura.output.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la disponibilidad de un producto en la BD
 * @author Jeison Andrés Vallejo Gómez
 * @author María Camila Hoyos Gómez
 * @author Jose Guillermo Segura Casas
 */

@Entity
@Table(name = "disponibility")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DisponibilidadEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;/**Identificador único de la disponibilidad*/

    private boolean disponible;/**Permite ver si un producto está disponible o no*/

    private int stock;/**Cantidad de productos disponibles*/

    @OneToOne
    @JoinColumn(name = "productId", unique = true)
    private ProductEntity producto;
}
