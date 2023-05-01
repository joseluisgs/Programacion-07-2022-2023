package es.joseluisgs.xml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor // Constructor con los final
@NoArgsConstructor // Necesario para Jackson y Lombok Es el constrcutor vacío
public class Perro {
    private UUID uuid = UUID.randomUUID();
    private String nombre;
    private int edad;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Perro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}

