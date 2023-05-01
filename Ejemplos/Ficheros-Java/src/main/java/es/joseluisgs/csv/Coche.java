package es.joseluisgs.csv;

import java.time.LocalDate;

public class Coche {

    private static int contador = 0;
    private final int id;
    private String modelo;
    private double precio;
    private LocalDate fechaLanzamiento;

    public Coche(String modelo, double precio, LocalDate fechaLanzamiento) {
        this.id = ++contador;
        this.modelo = modelo;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Coche(int id, String modelo, double precio, LocalDate fechaLanzamiento) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Coche.contador = contador;
    }

    public static Coche randomCoche() {
        return new Coche("Modelo " + (int) (Math.random() * 100), Math.random() * 10000, LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", fechaLanzamiento=" + fechaLanzamiento +
                '}';
    }
}
