package texto

import java.io.File

private data class Alumno(val numero: Int, val nombre: String, val repetidor: Boolean)

val programPath = System.getProperty("user.dir")
val fileOrigen = "$programPath${File.separator}data${File.separator}alumnos.txt"
fun main() {
    escribirAlumnosInit()
    mostrarAlumnos()
}

private fun mostrarAlumnos() {
    val fileText = File(fileOrigen)
    val alumnos = mutableListOf<Alumno>()
    // Esta vez lo debemos hacer con un while y bufferedReader
    fileText.bufferedReader().use {
        // De esta manera sabemos si hay más líneas que leer
        while (it.ready()) {
            val numero = it.readLine().toInt()
            val nombre = it.readLine().trim()
            val repetidor = it.readLine().toBoolean()
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
    val fileText = File(fileOrigen)
    fileText.writeText("")
    alumnos.forEach {
        fileText.appendText(it.numero.toString() + "\n")
        fileText.appendText(it.nombre + "\n")
        fileText.appendText(it.repetidor.toString() + "\n")
    }
}
