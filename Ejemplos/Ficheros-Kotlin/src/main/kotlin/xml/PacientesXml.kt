package xml

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.core.Persister
import java.io.File
import java.time.LocalDateTime
import java.util.*

data class Paciente(
    val uuid: UUID = UUID.randomUUID(),
    val nombre: String,
    val edad: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)

// Para evitar los problemas de los tipos usamos un DTO con los tipos String
// Field es para poder escribir y param para crear el constructor
@Root(name = "paciente")
data class PacienteDto(
    @field:Attribute(name = "uuid") // es un atributo
    @param:Attribute(name = "uuid") // es un atributo
    val uuid: String,

    @field:Element(name = "nombre") // Si queremos cambiar el nombre del elemento
    @param:Element(name = "nombre") // Si queremos cambiar el nombre del elemento
    val nombre: String,

    @field:Element(name = "edad")
    @param:Element(name = "edad")
    val edad: String,

    @field:Element(name = "created_at")
    @param:Element(name = "created_at")
    val createdAt: String,
)

// Ademas de la clase Paciente, necesitamos una clase que se encargue de convertir
// entre Paciente y PacienteDTO
fun Paciente.toDto() = PacienteDto(
    uuid = uuid.toString(),
    nombre = nombre,
    edad = edad.toString(),
    createdAt = createdAt.toString(),
)

fun PacienteDto.toPaciente() = Paciente(
    uuid = UUID.fromString(uuid),
    nombre = nombre,
    edad = edad.toInt(),
    createdAt = LocalDateTime.parse(createdAt),
)

// Para las listas, necesitamos una clase contenedora
@Root(name = "pacientes_list")
data class PacientesDto(
    @field:ElementList(name = "pacientes", inline = true) // inline para que no se cree un elemento contenedor
    @param:ElementList(name = "pacientes", inline = true) // inline para que no se cree un elemento contenedor
    val pacientes: List<PacienteDto>,
)

fun List<Paciente>.toDto() = PacientesDto(pacientes = map { it.toDto() })
fun PacientesDto.toPacientes() = pacientes.map { it.toPaciente() }

fun main() {
    val paciente = Paciente(nombre = "Juan", edad = 30)
    println(paciente)

    val programPath = System.getProperty("user.dir")
    val fichero = "$programPath${File.separator}data${File.separator}paciente.xml"

    val serializer = Persister()
    serializer.write(paciente.toDto(), File(fichero));

    println("Escrito de $fichero")

    val paciente2 = serializer
        .read(PacienteDto::class.java, File(fichero))
        .toPaciente()
    println(paciente2)

    // Vamos con una lista
    val pacientes = listOf(
        Paciente(nombre = "Juan", edad = 30),
        Paciente(nombre = "Ana", edad = 25),
        Paciente(nombre = "Luis", edad = 40),
        Paciente(nombre = "María", edad = 35),
    )

    val fichero2 = "$programPath${File.separator}data${File.separator}pacientes.xml"
    serializer.write(pacientes.toDto(), File(fichero2));
    println("Escrito de $fichero2")

    val pacientes2 = serializer
        .read(PacientesDto::class.java, File(fichero2))
        .toPacientes()
    println(pacientes2)

}