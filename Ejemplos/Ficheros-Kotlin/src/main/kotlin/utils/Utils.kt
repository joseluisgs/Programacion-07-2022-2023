package utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.Path

fun main() {
    // Path es una clase que representa una ruta de fichero
    val programPath = Path(System.getProperty("user.dir"))
    println(programPath.toString())
    println(programPath.fileName)
    println(programPath.isAbsolute)
    println(programPath.parent)
    println(programPath.root)
    println(programPath.subpath(0, 2))

    // File es una clase que representa un fichero
    val fichero = programPath.resolve("data").resolve("fichero-texto.txt").toFile()
    println(fichero.absolutePath)
    println(fichero.name)
    println(fichero.extension)
    println(fichero.parent)
    println(fichero.freeSpace)
    println(fichero.isDirectory)
    println(fichero.isFile)
    println(fichero.isHidden)
    println(fichero.lastModified())
    println(fichero.length())

    // comando ls ls sobre el directorio path
    println("Listando ficheros del directorio")
    if (programPath.toFile().isDirectory) {
        programPath.toFile().listFiles()?.forEach {
            print(it.name) // nombre del fichero
            print(" ") // espacio
            print(it.length()) // tamaño del fichero
            print(" ") // espacio
            print(it.lastModified()) // fecha de modificación
            print(" ") // espacio
            print(it.absolutePath) // nombre del fichero
            print(" ") // espacio
            print(it.canRead()) // si se puede leer
            println(" ") // espacio
        }

    }

    // mirar todos los métodos de la clase File y Path

    // Paths es una clase que representa una ruta de fichero dentro de Nio2
    // trabaja con la api Stream y es asincrono (no bloqueante)
    val path = Paths.get(programPath.toString(), "data", "fichero-texto.txt")
    println(path.toString())

    println(Files.getAttribute(path, "creationTime"))
    Files.deleteIfExists(Path(programPath.toString(), "data", "fichero-texto-copy.kaka.txt"))
    // Files.createDirectory(Path(programPath.toString(), "data", "fichero-texto-copy.kaka.txt"))

    // Resources es la carpeta de recursos
    // se usa para meter recursos de solo lectura
    // se usa para meter ficheros de configuración
    // se usa para meter ficheros de datos
    // siempre esta en el classpath

    // Classpath es la ruta donde se encuentran los ficheros compilados
    var ficherosResources = ClassLoader.getSystemClassLoader().getResource("coches.csv")
    println(ficherosResources)
    println(ficherosResources?.readText())

    ficherosResources =
        ClassLoader.getSystemClassLoader().getResource("data" + File.separator + "fichero-texto.txt")
    println(ficherosResources)
    println(ficherosResources?.readText())

    // Ficheros de propiedades
    // Son fichero clave valor para guardar configuraciones o cualquier información
    // Se usan mucho en aplicaciones de servidor

    val properties = Properties()
    // Cargamos el fichero de propiedades
    properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties"))
    println(properties.getProperty("nombre") ?: "No existe")
    println(properties.getProperty("edad") ?: "No existe")
    println(properties.getProperty("curso.modulo") ?: "No existe")


}