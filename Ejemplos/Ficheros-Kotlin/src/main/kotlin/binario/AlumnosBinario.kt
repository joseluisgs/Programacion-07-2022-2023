package binario

import java.io.File

private data class Alumno(val numero: Int, val nombre: String, val repetidor: Boolean)

val programPath = System.getProperty("user.dir")
val fileOrigen = "$programPath${File.separator}data${File.separator}alumnos.txt"
fun main() {
    escribirAlumnosInit()
    mostrarAlumnos()
}

private fun mostrarAlumnos() {
    val file = File(fileOrigen)
    val alumnos = mutableListOf<Alumno>()
    file.inputStream().buffered().use {
        while (it.available() > 0) {
            // Recorremos caracter a caracter hasta encontrar un salto de línea
            val numeroString = StringBuilder()
            var char = it.read().toChar()
            while (char != '\n') {
                numeroString.append(char)
                char = it.read().toChar()
            }
            val numero = numeroString.toString().toInt()
            // println("Número: $numero")
            // lee un nombre
            val nombreString = StringBuilder()
            char = it.read().toChar()
            while (char != '\n') {
                nombreString.append(char)
                char = it.read().toChar()
            }
            val nombre = nombreString.toString()
            // println("Nombre: $nombre")
            // lee un booleano
            val repetidorString = StringBuilder()
            char = it.read().toChar()
            while (char != '\n') {
                repetidorString.append(char)
                char = it.read().toChar()
            }
            val repetidor = repetidorString.toString().toBoolean()
            // println("Repetidor: $repetidor")
            alumnos.add(Alumno(numero, nombre, repetidor))
        }
    }
    println("Alumnos: " + alumnos.size)
    alumnos.forEach { println(it) }
}

private fun escribirAlumnosInit() {
    val alumnos = listOf(
        Alumno(1, "Juan", false),
        Alumno(2, "Ana", true),
        Alumno(3, "Luis", false),
        Alumno(4, "María", true),
        Alumno(5, "Pedro", false),
        Alumno(6, "Lucía", true),
        Alumno(7, "Marta", false),
        Alumno(8, "Javier", true),
        Alumno(9, "Sara", false),
        Alumno(10, "Pablo", true),
    )
    // fichero de texto
    val fileText = File(texto.fileOrigen)
    fileText.outputStream().buffered().use {
        for (alumno in alumnos) {
            it.write(alumno.numero.toString().toByteArray())
            it.write("\n".toByteArray())
            it.write(alumno.nombre.toByteArray())
            it.write("\n".toByteArray())
            it.write(alumno.repetidor.toString().toByteArray())
            it.write("\n".toByteArray())
        }
    }
}


