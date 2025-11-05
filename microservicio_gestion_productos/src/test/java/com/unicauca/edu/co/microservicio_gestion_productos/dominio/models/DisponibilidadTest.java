package com.unicauca.edu.co.microservicio_gestion_productos.dominio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadTest {

    private Disponibilidad disponibilidad;

    @BeforeEach
    void setUp() {
        disponibilidad = new Disponibilidad();
        disponibilidad.setDisponible(true);
        disponibilidad.setStock(10);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange & Act
        Disponibilidad nuevaDisponibilidad = new Disponibilidad();
        nuevaDisponibilidad.setDisponible(false);
        nuevaDisponibilidad.setStock(5);

        // Assert
        assertFalse(nuevaDisponibilidad.isDisponible());
        assertEquals(5, nuevaDisponibilidad.getStock());
    }

    @Test
    void testChangeDisposition_ReturnsOppositeValue() {
        // Arrange
        disponibilidad.setDisponible(true);

        // Act
        boolean resultado = disponibilidad.changeDisposition(true);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void testChangeDisposition_FromFalse_ReturnsTrue() {
        // Arrange
        disponibilidad.setDisponible(false);

        // Act
        boolean resultado = disponibilidad.changeDisposition(false);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void testConstructor_WithNoArgs() {
        // Act
        Disponibilidad nuevaDisponibilidad = new Disponibilidad();

        // Assert
        assertNotNull(nuevaDisponibilidad);
    }

    @Test
    void testConstructor_WithAllArgs() {
        // Act
        Disponibilidad nuevaDisponibilidad = new Disponibilidad(true, 15);

        // Assert
        assertTrue(nuevaDisponibilidad.isDisponible());
        assertEquals(15, nuevaDisponibilidad.getStock());
    }
}
