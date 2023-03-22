package es.joseluisgs.binario;

import java.io.*;

public class Binario {
    public static void main(String[] args) {
        leerFichero();
        escribirFichero();
    }

    private static void escribirFichero() {
        String myPath = System.getProperty("user.dir");
        String myFile = myPath + File.separator + "data" + File.separator + "fichero-texto-2.txt";
        System.out.println(myFile);
        // Existe y podemos leerlo?
        File fDestino = new File(myFile);

        try (FileOutputStream fileOutputStream = new FileOutputStream(fDestino)) {
            String texto = "Hola mundo";
            fileOutputStream.write(texto.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fDestino))) {
            String texto = "Hola mundo";
            bufferedOutputStream.write(texto.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void leerFichero() {
        String myPath = System.getProperty("user.dir");
        String myFile = myPath + File.separator + "data" + File.separator + "fichero-texto.txt";
        System.out.println(myFile);
        // Existe y podemos leerlo?
        File fOrigen = new File(myFile);

        try (FileInputStream fileInputStream = new FileInputStream(fOrigen)) {
            int singleCharInt;
            char singleChar;

            while((singleCharInt = fileInputStream.read()) != -1) {
                singleChar = (char) singleCharInt;
                System.out.print(singleChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fOrigen))) {
            int singleCharInt;
            char singleChar;
            while((singleCharInt = bufferedInputStream.read()) != -1) {
                singleChar = (char) singleCharInt;
                System.out.print(singleChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
