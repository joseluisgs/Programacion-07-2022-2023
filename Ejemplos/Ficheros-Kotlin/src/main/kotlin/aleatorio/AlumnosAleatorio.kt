package aleatorio

import java.io.File
import java.io.RandomAccessFile

private data class Alumno(val numero: Int, val nombre: String, val repetidor: Boolean)

val programPath = System.getProperty("user.dir")
val fileOrigen = "$programPath${File.separator}data${File.separator}alumnos.dat"
fun main() {
    escribirAlumnosInit()
    mostrarAlumnos()
}

private fun mostrarAlumnos() {
    val fileRandom = RandomAccessFile(fileOrigen, "r")
    val alumnos = mutableListOf<Alumno>()
    fileRandom.use {
        // fileRandom.seek(0)
        while (it.filePointer < it.length()) {
            val numero = it.readInt() // 4 bytes
            val nombre = it.readUTF() // 2 bytes + longitud
            val repetidor = it.readBoolean() // 1 byte
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
    val fileRandom = RandomAccessFile(fileOrigen, "rw")
    fileRandom.use {
        // fileRandom.seek(it.length()) // Posiciona el puntero al final del fichero
        // fileRandom.seek(0) // Posiciona el puntero al principio del fichero
        for (alumno in alumnos) {
            it.writeInt(alumno.numero)
            it.writeUTF(alumno.nombre)
            it.writeBoolean(alumno.repetidor)
        }
    }
}
