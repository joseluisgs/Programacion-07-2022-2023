package serializable

import java.io.*


// Para ser serializable todas las propiedades deben ser serializables (sublases incluídas)
private data class Persona(val nombre: String, val edad: Int) : Serializable

fun main() {
    escribirFicheroSerializable()
    leerFicheroSerializable()
}

fun leerFicheroSerializable() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-serializable.dat"
    println("Leyendo fichero: $filePath")

    val file = File(filePath)

    val input = ObjectInputStream(FileInputStream(file))
    input.use {
        // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección
        val persona = it.readObject() as Persona
        val lista = it.readObject() as List<Persona>
        println(persona)
        println(lista)
    }
}

fun escribirFicheroSerializable() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-serializable.dat"
    println("Escribiendo fichero: $filePath")

    val file = File(filePath)
    val persona = Persona("Antonio", 34)
    val lista = listOf(
        Persona("Pepe", 23),
        Persona("Juan", 34),
        Persona("Ana", 45)
    )
    val output = ObjectOutputStream(FileOutputStream(file))
    output.use {
        // Podemos escribir cualquier objeto serializable, ya sea un objeto o una colección
        it.writeObject(persona)
        it.writeObject(lista)
    }
}
