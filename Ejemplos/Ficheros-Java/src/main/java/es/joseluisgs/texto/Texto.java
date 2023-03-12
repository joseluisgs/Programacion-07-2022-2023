package es.joseluisgs.texto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Texto {
    public static void main(String[] args) {
        leerFichero();
        escribirFichero();
        copiarFichero();
    }

    private static void copiarFichero() {
        String myPath = System.getProperty("user.dir");
        String myFile = myPath + File.separator + "data" + File.separator + "fichero-texto.txt";
        System.out.println(myFile);
        // Existe y podemos leerlo?
        File fOrigen = new File(myFile);
        if (fOrigen.exists() && fOrigen.canRead()) {
            System.out.println("Existe y podemos leerlo");
        } else {
            System.out.println("No existe o no podemos leerlo");
        }
        String myCopy = myPath + File.separator + "data" + File.separator + "fichero-texto-copy.txt";
        File fCopy = new File(myCopy);

        // leemos con BufferReader/FileReader y escribimos con PrintWriter/FileWriter
        try (BufferedReader br = new BufferedReader(new FileReader(fOrigen))) {
            // OJO si no existe lo crea, si existe lo sobreescribe
            try (PrintWriter pw = new PrintWriter(new FileWriter(fCopy))) {
                String linea;
                while ((linea = br.readLine())!= null) {
                    pw.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al copiar" + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fOrigen))) {
            // OJO si no existe lo crea, si no añade al final
            try (PrintWriter pw = new PrintWriter(new FileWriter(fCopy, true))) {
                String linea;
                while ((linea = br.readLine())!= null) {
                    pw.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al copiar" + e.getMessage());
        }

        // Usando NIO y api stream y printWriter
        // Si el fichero es muy grande, lo escribimos en trozos
        try (Stream<String> stream = Files.lines(fOrigen.toPath())) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(fCopy))) {
                stream.forEach(pw::println);
            }
        } catch (IOException e) {
            System.out.println("Error al copiar" + e.getMessage());
        }

        // Si no es muy grande lo leemos todo y lo escribimos linea a linea
        try {
            List<String> lineas = Files.readAllLines(fOrigen.toPath());
            try (PrintWriter pw = new PrintWriter(new FileWriter(fCopy))) {
                for (String linea : lineas) {
                    pw.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al copiar" + e.getMessage());
        }

    }

    private static void escribirFichero() {
        String myPath = System.getProperty("user.dir");
        String myFile = myPath + File.separator + "data" + File.separator + "fichero-texto-2.txt";
        System.out.println(myFile);

        File f = new File(myFile);
        if (f.exists()) {
            System.out.println("Existe");
        } else {
            System.out.println("No existe");
        }
        System.out.println();

        // Escribimos fichero
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Hola");
            bw.newLine();
            bw.write("Adios");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            System.out.println("Fin de escritura");
        }

        // autoClose try catch
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write("Hola");
            bw.newLine();
            bw.write("Adios");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            System.out.println("Fin de escritura");
        }

        // PrintWriter
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f));
            pw.println("Hola");
            pw.println("Adios");
            pw.close();
        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            System.out.println("Fin de escritura");
        }

        // PrintWriter autoClose
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println("Hola");
            pw.println("Adios");
        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            System.out.println("Fin de escritura");
        }


    }

    private static void leerFichero() {
        System.out.println("Hola Leer Ficheros");

        // Directorio de ejecución
        System.out.println(System.getProperty("user.dir"));
        String myPath = System.getProperty("user.dir");
        System.out.println(myPath);
        String myFile = myPath + File.separator + "data" + File.separator + "fichero-texto.txt";
        System.out.println(myFile);

        File f = new File(myFile);
        if (f.exists()) {
            System.out.println("Existe");
        } else {
            System.out.println("No existe");
        }
        System.out.println();

        // Existe??
        if (f.exists()) {
            System.out.println("Existe");
        } else {
            System.out.println("No existe");
        }
        System.out.println();

        // Es un directorio?
        if (f.isDirectory()) {
            System.out.println("Es un directorio");
        } else {
            System.out.println("No es un directorio");
        }
        System.out.println();

        // Tenemos permisos de lectura?
        if (f.canRead()) {
            System.out.println("Tenemos permisos de lectura");
        } else {
            System.out.println("No tenemos permisos de lectura");
        }

        System.out.println();
        BufferedReader brFuera = null;
        try {
            brFuera = new BufferedReader(new FileReader(f));
            // Vamos leyendo linea a linea hasta que no haya más
            String linea = brFuera.readLine();
            while (linea != null) {
                // Procesamos la línea
                System.out.println(linea);
                linea = brFuera.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            System.out.println("Fin de lectura");
            if (brFuera != null) {
                try {
                    brFuera.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero");
                }
            }
        }

        // Leemos con bufferReader
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            // Vamos leyendo linea a linea hasta que no haya más
            String linea = br.readLine();
            while (linea != null) {
                // Procesamos la línea
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            System.out.println("Fin de lectura");
        }
        System.out.println();

        // Con un inputStream
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(f));
            BufferedReader br = new BufferedReader(is);
            // Vamos leyendo linea a linea hasta que no haya más
            String linea = br.readLine();
            while (linea != null) {
                // Procesamos la línea
                System.out.println(linea);
                linea = br.readLine();
            }brFuera.close();
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error de lectura");
        }
        finally {
            System.out.println("Fin de lectura");
        }

        // Con autoclese try catch
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            // Vamos leyendo linea a linea hasta que no haya más
            String line;
            while((line = br.readLine()) != null) {
                // Procesamos la línea
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }

        // Con autoclese try catch y Scanner
        try (Scanner sc = new Scanner(f)) {
            // Vamos leyendo linea a linea hasta que no haya más
            while(sc.hasNextLine()) {
                // Procesamos la línea
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
        System.out.println();

        // Con la api stream
        // // Ideal cuando tenemos ficheros muy grandes y procesamos linea a linea
        try (Stream<String> stream = Files.lines(f.toPath())) {
            stream.forEach(System.out::println); // Procesamos linea a linea
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }

        try {
            // Procesamos linea a linea
            Files.readAllLines(f.toPath()).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }
}
