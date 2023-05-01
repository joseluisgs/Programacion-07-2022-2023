import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory

fun main(args: Array<String>) {
    println("Hola Temp y Zip")

    // Creamos un directorio temporal
    val tempDir = createTempDirectory("myTempDir")
    println("Directorio temporal: $tempDir")

    // ya es como un directorio normal, podemos copiar archivos
    Files.copy(
        File("build.gradle.kts").toPath(),
        tempDir.resolve("build.gradle.kts"),
        StandardCopyOption.REPLACE_EXISTING
    )
    Files.copy(
        File("settings.gradle.kts").toPath(),
        tempDir.resolve("settings.gradle.kts"),
        StandardCopyOption.REPLACE_EXISTING
    )

    // Listamos el contenido del directorio
    println("Contenido del directorio temporal:")
    tempDir.toFile().listFiles()?.forEach { if (it.isFile) println(it.name) }

    // Cremaos un archivo zip, podría ser  ddonde tu quisieras
    // primerlo los dicheros
    // podemos hacerlo como antes o con walk para usar streams
    val archivos = tempDir.toFile().walk().filter { it.isFile }.toList()

    // creamos el zip en otro directorio temporal
    val tempZipDir = createTempDirectory("myTempZip")
    val tempZip = tempZipDir.resolve("zip.zip")

    // Comprimimos los ficheros en el zip
    ZipOutputStream(Files.newOutputStream(tempZip)).use { zip ->
        archivos.forEach { archivo ->
            println("Comprimiendo: ${archivo.name} en ${tempZip.toAbsolutePath()}")
            zip.putNextEntry(ZipEntry(archivo.name))
            Files.copy(archivo.toPath(), zip)
            zip.closeEntry()
        }
    }

    // Listamos el contenido del zip si queremos
    println("Contenido del zip:")
    ZipFile(tempZip.toFile()).entries().asSequence().forEach { println(it.name) }

    // creamos otro directorio temporal para descomprimir
    val tempDir2 = createTempDirectory("myTempDir2")
    println("Directorio temporal: $tempDir2")

    // Descomprimimos el zip en el directorio temporal
    ZipFile(tempZip.toFile()).use { zip ->
        zip.entries().asSequence().forEach { entry ->
            println("Descomprimiendo: ${entry.name} en ${tempDir2.fileName}")
            zip.getInputStream(entry).use { input ->
                Files.copy(input, tempDir2.resolve(entry.name))
            }
        }
    }

    // Listamos el contenido del directorio
    println("Contenido del directorio temporal:")
    tempDir2.toFile().listFiles()?.forEach { if (it.isFile) println(it.name) }

    // Tambien podemos crear ficheros temporales
    val tempFile = File.createTempFile("myTempFile", ".txt")
    println("Fichero temporal: ${tempFile.absolutePath}")
    // Podemos escribir el listado de ficheros de este proyecto allí
    tempFile.writeText(archivos.joinToString("\n") { it.name })
    // Y finalmente mostrarlo
    println("Contenido del fichero temporal:")
    println(tempFile.readText())

    // Borramos el directorio temporal
    tempDir.toFile().deleteRecursively() // Son directorios que pueden tener ficheros
    tempZipDir.toFile().deleteRecursively()
    tempZip.toFile().deleteRecursively()
    tempDir2.toFile().deleteRecursively()
    tempFile.deleteOnExit() // Es un fichero, por eso podemos borrar al salir


}