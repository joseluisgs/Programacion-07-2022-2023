package texto

import java.io.File

fun main() {
    println("Hola ficheros de texto!")
    // leerFicheroTexto()
    // escribirFicheroTexto()
    copiarFicheroTexto()
}

fun copiarFicheroTexto() {
    val programPath = System.getProperty("user.dir")
    val fileOrigenPath = "$programPath${File.separator}data${File.separator}fichero-texto.txt"
    val fileDestinoPath = "$programPath${File.separator}data${File.separator}fichero-texto-copy.txt"

    val fileOrigen = File(fileOrigenPath)
    val fileDestino = File(fileDestinoPath)

    if (fileOrigen.exists() && fileOrigen.canRead()) {
        println("Copiando fichero...")
    } else {
        println("No se puede copiar el fichero")
        return
    }

    // Copia el fichero
    // Leemos todas las líneas y las escribimos en el fichero destino
    fileDestino.writeText("") // Limpiamos el fichero
    fileOrigen.readLines().forEach { fileDestino.appendText("$it\n") }

    fileDestino.writeText("") // Limpiamos el fichero
    // ForEachLine es equivalente a readLines().forEach
    fileOrigen.forEachLine { fileDestino.appendText("$it\n") }

    // Cuidado con ficheros grandes
    fileOrigen.readText().also { fileDestino.writeText(it) }

    // forEachLine y printWriter
    fileOrigen.forEachLine { fileDestino.printWriter().println(it) }

    // forEachLine y bufferedWriter
    fileOrigen.forEachLine { fileDestino.bufferedWriter().write("$it\n") }


    // Usando buffer de lectura o escritura
    fileOrigen.bufferedReader().useLines { lines ->
        fileDestino.bufferedWriter().use { out ->
            lines.forEach { out.write("$it\n") }
        }
    }

    // con todas las combinaciones que conozcas


}

fun escribirFicheroTexto() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-texto-2.txt"
    println("Leyendo fichero: $filePath")

    val fileDestino = File(filePath)

    // Con writeText escribimos todo el contenido del fichero
    fileDestino.writeText("Hola mundo")

    // Podemos añadir contenido al fichero
    fileDestino.appendText("Hola de nuevo")

    // Podemos usar el método writeBytes para escribir bytes
    fileDestino.writeBytes("Hola mundo".toByteArray())

    // printWriter
    fileDestino.printWriter().use { it.println("Hola mundo") }

    // bufferedWriter
    fileDestino.bufferedWriter().use { it.write("Hola mundo") }

    // bufferedWriter + append
    fileDestino.bufferedWriter().use { it.append("Hola mundo") }

    // printWriter + append
    fileDestino.printWriter().use { it.append("Hola mundo") }

}

fun leerFicheroTexto() {
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}data${File.separator}fichero-texto.txt"
    println("Leyendo fichero: $filePath")

    val fileOrigen = File(filePath)

    // existe
    if (fileOrigen.exists()) {
        println("El fichero existe")
    } else {
        println("El fichero no existe")
    }

    // Si puedo leerlo
    if (fileOrigen.canRead()) {
        println("El fichero se puede leer")
    } else {
        println("El fichero no se puede leer")
    }
    println()

    // Devuelve una lista de líneas
    fileOrigen.readLines().forEach { println(it) }
    println()

    // Procesa linea a linea
    fileOrigen.forEachLine { println(it) }

    // Lee el fichero completo en una cadena
    fileOrigen.readText().also { println(it) }

    // Usa usa una secuencia y se le procesa un callback en el lambda
    fileOrigen.useLines { it.toList() }.forEach { println(it) }
    // fileOrigen.useLines { it.forEach { line -> println(line) } }
    println()

    // Buffer de lectura
    // Lee el fichero completo en una lista de cadena
    fileOrigen.bufferedReader().readLines().forEach { println(it) }
    fileOrigen.bufferedReader().forEachLine { println(it) }
    println()
    fileOrigen.bufferedReader().readLine().let { println(it) }
    println()
    // Lee todo el fichero en una cadena
    fileOrigen.bufferedReader().readText().let { println(it) }
    println()

    // Use equivale al try-with-resources de Java
    // Dentro del use podemos hacer uso de los métodos de BufferedReader
    fileOrigen.bufferedReader().use { it.readText() }.let { println(it) }
    println()

    // InputStream
    fileOrigen.inputStream().readBytes().toString(Charsets.UTF_8).let { println(it) }
    println()

    // InputStreamReader
    fileOrigen.inputStream().reader().readText().let { println(it) }
    println()

    // InputStream + BufferedReader
    fileOrigen.inputStream().bufferedReader().readText().let { println(it) }
    println()

    // InputStreamReader + BufferedReader + use
    fileOrigen.inputStream().reader().buffered().use { it.readText() }.let { println(it) }
    println()
}
