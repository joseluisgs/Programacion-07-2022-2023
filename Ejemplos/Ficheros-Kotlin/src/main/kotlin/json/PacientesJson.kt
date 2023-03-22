package json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.buffer
import okio.source
import java.io.File
import java.time.LocalDateTime
import java.util.*

private data class Paciente(
    val uuid: UUID = UUID.randomUUID(),
    val nombre: String,
    val edad: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)

@ExperimentalStdlibApi // Para usar el método DE adapter con enlazado de Kotlin
fun main(args: Array<String>) {
    val paciente = Paciente(nombre = "Juan", edad = 30)
    println(paciente)

    val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .add(LocalDateTimeAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val jsonAdapter = moshi.adapter<Paciente>()
    var json = jsonAdapter.indent("   ").toJson(paciente)
    println(json)
    // Con mi función de extensión
    json = jsonAdapter.toPrettyJson(paciente)
    println(json)

    val paciente2 = jsonAdapter.fromJson(json)
    println(paciente2)

    // Escribir en un fichero
    val programPath = System.getProperty("user.dir")
    val fichero = "$programPath${File.separator}data${File.separator}paciente.json"

    File(fichero).writeText(json)

    // Leer de un fichero
    val paciente3 = jsonAdapter.fromJson(File(fichero).readText())
    println(paciente3)

    // Lista de pacientes
    val pacientes = listOf(
        Paciente(nombre = "Juan", edad = 30),
        Paciente(nombre = "Ana", edad = 25),
        Paciente(nombre = "Luis", edad = 40),
        Paciente(nombre = "María", edad = 35),
    )

    val jsonAdapter2 = moshi.adapter<List<Paciente>>()
    json = jsonAdapter2.indent("   ").toJson(pacientes)
    println(json)
    // Con mi función de extensión
    json = jsonAdapter2.toPrettyJson(pacientes)
    println(json)

    val pacientes2 = jsonAdapter2.fromJson(json)
    println(pacientes2)

    // Escribir en un fichero
    val fichero2 = "$programPath${File.separator}data${File.separator}pacientes.json"
    File(fichero2).writeText(json)

    // Leer de un fichero
    val pacientes3 = jsonAdapter2.fromJson(File(fichero2).readText())
    println(pacientes3)

    // Si el fichero es grande, usar un buffer
    val pacientes4 = jsonAdapter2.fromJson(File(fichero2).inputStream().source().buffer())
    println(pacientes4)
}