package binario

import java.io.File

fun main() {
    leerFichero()
    escribirFichero()
}

fun escribirFichero() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-texto-2.txt"
    println("Escribiendo fichero: $filePath")

    val fileDestino = File(filePath)

    // writeBytes
    fileDestino.writeBytes("Hola mundo!".toByteArray())

    // outputStream
    fileDestino.outputStream().use { it.write("Hola mundo!".toByteArray()) }

    // bufferedOutputStream
    fileDestino.outputStream().buffered().use { it.write("Hola mundo!".toByteArray()) }

    // con bufferedWriter
    fileDestino.bufferedWriter().use { it.write("Hola mundo!") }
}

fun leerFichero() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-texto.txt"
    println("Leyendo fichero: $filePath")

    val fileOrigen = File(filePath)

    // readBytes
    val bytes = fileOrigen.readBytes().toString(Charsets.UTF_8)
    println(bytes)
    println()
    // con un inputStream
    fileOrigen.inputStream().use { println(it.readBytes().toString(Charsets.UTF_8)) }
    println()
    // con buffered
    fileOrigen.inputStream().buffered().use { println(it.readBytes().toString(Charsets.UTF_8)) }
}
