package es.joseluisgs.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import es.joseluisgs.json.utils.LocalDateTimeAdapter;
import es.joseluisgs.json.utils.UuidAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

public class PacienteJson {
    final static Logger logger = LoggerFactory.getLogger(PacienteJson.class);

    public static void main(String[] args) {
        logger.info("Ejemplo de JSON con Moshi");
        // Creamos un objeto Paciente
        Paciente paciente = new Paciente("José Luis", 45);
        logger.info("Paciente: " + paciente);
        System.out.println("Paciente: " + paciente);

        // Moshi a JSON
        Moshi moshi = new Moshi.Builder()
                .add(new LocalDateTimeAdapter()) // Añadimos los adaptadores de fecha y UUID
                .add(new UuidAdapter())
                .build();
        String json = moshi.adapter(Paciente.class).toJson(paciente);
        logger.info("JSON: " + json);
        System.out.println(json);

        // si queremos pretty print
        json = moshi.adapter(Paciente.class).indent("  ").toJson(paciente);
        System.out.println(json);

        // JSON a Moshi
        try {
            Paciente paciente2 = moshi.adapter(Paciente.class).fromJson(json);
            System.out.println(paciente2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Salvamos a fichero
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "paciente.json";
        json = moshi.adapter(Paciente.class).indent("  ").toJson(paciente);

        // escribir a fichero
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println(json);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

        // leer fichero
        try {
            // Procesamos linea a linea
            json = Files.readString(new File(path).toPath());
            Paciente paciente3 = moshi.adapter(Paciente.class).fromJson(json);
            System.out.println(paciente3);
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }

        // Lista de pacientes
        List<Paciente> pacientes = List.of(
                new Paciente("José Luis", 45),
                new Paciente("María", 25),
                new Paciente("Juan", 35)
        );

        // Convertimos a JSON
        // Para tipos complejos necesitamos un JsonAdapter
        Type type = Types.newParameterizedType(List.class, Paciente.class);
        JsonAdapter<List<Paciente>> jsonAdapter = moshi.adapter(type);
        json = jsonAdapter.indent("  ").toJson(pacientes);
        System.out.println(json);

        // Convertimos a lista de pacientes
        try {
            List<Paciente> pacientes2 = jsonAdapter.fromJson(json);
            System.out.println(pacientes2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Salvamos a fichero
        path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "pacientes.json";
        json = jsonAdapter.indent("  ").toJson(pacientes);
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println(json);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

        // leer fichero
        try {
            // Procesamos linea a linea
            json = Files.readString(new File(path).toPath());
            List<Paciente> pacientes3 = jsonAdapter.fromJson(json);
            System.out.println(pacientes3);
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }
}
