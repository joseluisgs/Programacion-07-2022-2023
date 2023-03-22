package es.joseluisgs.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CsvFile {
    public static void main(String[] args) {
        // Creamos un fichero CSV
        ArrayList<Coche> coches = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            coches.add(Coche.randomCoche());
        }
        coches.forEach(System.out::println);

        System.out.println("Escritura del fichero");

        escribirCSV(coches);

        System.out.println("Lectura del fichero");

        var coches2 = leerCSV();

        if (coches2 != null) {
            coches2.forEach(System.out::println);
        } else {
            System.out.println("No se ha podido leer el fichero");
        }
    }

    private static List<Coche> leerCSV() {
        // Obtenemos el path
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "coches.csv";
        // Creamos el fichero
        File fichero = new File(path);

        try (Stream<String> stream = Files.lines(fichero.toPath())) {
            // ignoramos la primera linea por ser el encabezado
            return stream.skip(1)
                    // separamos por comas para obtener las columnas
                    .map(linea -> linea.split(","))
                    // mapeamos a un objeto coche y comprobamos o filtramos los datos
                    .map(columnas -> new Coche(
                            Integer.parseInt(columnas[0]), // id
                            columnas[1], // modelo
                            Double.parseDouble(columnas[2]), // precio
                            LocalDate.parse(columnas[3]) // fecha lanzamiento
                    )).toList();
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            System.out.println("Fin de lectura");
        }
        return null;
    }

    private static void escribirCSV(ArrayList<Coche> coches) {
        // Obtenemos el path
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "coches.csv";
        // Creamos el fichero
        File fichero = new File(path);
        // Creamos el fichero
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {
            // Escribir el encabezado
            pw.println("id,modelo,precio,fecha_lanzamiento");
            // Escribir los datos
            coches.forEach(coche -> pw.println(
                    coche.getId() + "," +
                            coche.getModelo() + "," +
                            coche.getPrecio() + "," +
                            coche.getFechaLanzamiento()
            ));

        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            System.out.println("Fin de escritura");
        }

    }
}
