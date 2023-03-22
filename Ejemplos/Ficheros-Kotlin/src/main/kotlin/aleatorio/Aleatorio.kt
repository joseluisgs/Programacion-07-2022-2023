package aleatorio

import java.io.File
import java.io.RandomAccessFile

fun main() {
    // leerFicheroAleatorioTexto()
    //mostrarFicheroAleatorio()
    //modificarFicheroAleatorio()
    //mostrarFicheroAleatorio()
    palabras()
}

fun palabras() {
    val programPath = System.getProperty("user.dir")
    val fileOrigen = "$programPath${File.separator}data${File.separator}texto.txt"
    val aleatorio = RandomAccessFile(fileOrigen, "rw")

    println("Introduce una palabra: ")
    val palabra = readlnOrNull() ?: throw Exception("Palabra no válida")
    println(palabra)
    aleatorio.use {
        var cadena = aleatorio.readLine()
        var posicion = aleatorio.filePointer
        while (cadena != null) {
            if (cadena.contains(palabra)) {
                // println(cadena)

                val indice = cadena.indexOf(palabra)
                val auxBuilder = StringBuilder(cadena)
                auxBuilder.replace(indice, indice + palabra.length, palabra.uppercase())
                cadena = auxBuilder.toString()

                aleatorio.seek(posicion)
                aleatorio.write(cadena.toByteArray())

            }
            posicion = aleatorio.filePointer
            cadena = aleatorio.readLine()
        }
    }
}

fun modificarFicheroAleatorio() {
    val programPath = System.getProperty("user.dir")
    val fileOrigen = "$programPath${File.separator}data${File.separator}enteros.dat"
    val aleatorio = RandomAccessFile(fileOrigen, "rw")
    // use!!!!!!!!
    aleatorio.use {
        it.seek(0)
        val longitud = it.length()
        val numEnteros = longitud / 4
        println("Numero de entradas: $numEnteros")
        // indicar el entero a modificar
        println("El entero a modificar [1-$numEnteros]: ")
        val entero = readlnOrNull()?.toIntOrNull() ?: 0
        if (entero !in 1..numEnteros) {
            println("El entero no es correcto")
            return
        }
        // posicionarse en el byte a modificar
        it.seek((entero - 1) * 4L)
        // leer el entero
        val valor = it.readInt()
        println("El valor del entero es: $valor")
        // pedir el nuevo valor
        println("Nuevo valor: ")
        val nuevoValor = readlnOrNull()?.toIntOrNull() ?: 0
        // modificar el entero
        it.seek((entero - 1) * 4L)
        it.writeInt(nuevoValor)
    }
}

fun mostrarFicheroAleatorio() {
    val programPath = System.getProperty("user.dir")
    val fileOrigen = "$programPath${File.separator}data${File.separator}enteros.dat"
    val aleatorio = RandomAccessFile(fileOrigen, "r")
    // posicionarse en el byte 0
    try {
        aleatorio.seek(0)
        // Longitud del fichero
        val longitud = aleatorio.length()
        // leemos todos los enteros
        var entero: Int
        val numEnteros = longitud / 4
        /*do {
            entero = aleatorio.readInt()
            println(entero)
        } while (true)*/
        for (i in 0 until numEnteros) {
            entero = aleatorio.readInt()
            println(entero)
        }
    } catch (e: Exception) {
        println("Excepcion  y Fin del fichero")
    } finally {
        aleatorio.close()
    }

}

fun leerFicheroAleatorioTexto() {
    val programPath = System.getProperty("user.dir")
    val fileOrigen = "$programPath${File.separator}data${File.separator}texto.txt"
    val aleatorio = RandomAccessFile(fileOrigen, "r")
    var linea: String?
    do {
        linea = aleatorio.readUTF()
        if (linea != null) {
            println(linea)
        }
    } while (linea != null)
    aleatorio.close()
}
