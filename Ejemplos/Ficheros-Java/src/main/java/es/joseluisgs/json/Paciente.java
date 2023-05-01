package es.joseluisgs.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor // Constructor con los final
@NoArgsConstructor // Necesario para Jackson y Lombok Es el constrcutor vacío
public class Paciente {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final UUID uuid = UUID.randomUUID();
    private String nombre;
    private int edad;

}
