package serializable


import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

private data class Alumno(val numero: Int, val nombre: String, val repetidor: Boolean) : Serializable

val programPath = System.getProperty("user.dir")
val fileOrigen = "$programPath${File.separator}data${File.separator}alumnos.dat"
fun main() {
    escribirAlumnosInit()
    mostrarAlumnos()
}

private fun mostrarAlumnos() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}alumnos-serializable.dat"
    println("Leyendo fichero: $filePath")

    val alumnos = mutableListOf<Alumno>()

    val input = ObjectInputStream(File(filePath).inputStream())
    input.use {
        // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección
        val alumnosLeidos = it.readObject() as List<Alumno>
        alumnos.addAll(alumnosLeidos)
        // podría leer uno a uno
        // while (it.available() > 0) {
        //     val alumno = it.readObject() as Alumno
        //     alumnos.add(alumno)
        // }
    }
    println("Alumnos: " + alumnos.size)
    alumnos.forEach { println(it) }
}

private fun escribirAlumnosInit() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}alumnos-serializable.dat"
    println("Escribiendo fichero: $filePath")

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
    val output = ObjectOutputStream(File(filePath).outputStream())
    output.use {
        // Podemos escribir cualquier objeto serializable, ya sea un objeto o una colección
        it.writeObject(alumnos)
        // podría escribir uno a uno
        // alumnos.forEach { alumno -> it.writeObject(alumno) }
    }
}
