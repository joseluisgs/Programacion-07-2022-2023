package es.joseluisgs.serializable;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializable {
    public static void main(String[] args) {
        System.out.println("Hola Serializable");

        escribirSerializable();
        leerSerializable();
    }

    private static void leerSerializable() {
        String path = System.getProperty("user.dir") + File.separator + "data";
        String personasFile = path + File.separator + "personas.dat";

        var personas = new ArrayList<Persona>();

        try (var ois = new ObjectInputStream(new FileInputStream(personasFile))) {
            // Podemos leer un objeto o coleccion de objetos
            personas = (ArrayList<Persona>) ois.readObject();
            // O poder leerlo de esta forma
            /*while (ois.available() > 0) {
                personas.add((Persona) ois.readObject());
            }*/
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Personas: " + personas.size());
        personas.forEach(System.out::println);
    }

    private static void escribirSerializable() {
        String path = System.getProperty("user.dir") + File.separator + "data";
        String personasFile = path + File.separator + "personas.dat";

        ArrayList<Persona> personas = new ArrayList<>(
                List.of(
                        new Persona("Pepe", 20),
                        new Persona("Juan", 30),
                        new Persona("Luis", 40)
                )
        );

        try (var oos = new ObjectOutputStream(new FileOutputStream(personasFile))) {
            // Podemos escribir un objeto o coleccion de objetos
            oos.writeObject(personas);
            // personas.forEach(p -> oos.writeObject(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Para que un objeto sea serializable debe implementar la interfaz java.io.Serializable
// y todos sus atributos deben ser serializables
class Persona implements java.io.Serializable {
    int edad;
    private String nombre;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }

}
