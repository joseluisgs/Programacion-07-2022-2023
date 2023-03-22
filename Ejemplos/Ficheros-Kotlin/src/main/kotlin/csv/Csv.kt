package csv

import java.io.File
import java.time.LocalDate

fun main() {
    println("Hola Csv")

    val coches = List(10) { Coche.randomCoche() }
    coches.forEach { println(it) }

    println("Escribiendo en CSV")
    escribirCsv(coches)

    println("Leyendo de CSV")
    val cochesLeidos = leerCsv()
    cochesLeidos.forEach { println(it) }
}

fun leerCsv(): List<Coche> {
    val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}coches.csv"
    val fichero = File(path)

    // De esta manera lo procesamos todo en memoria, como colección
    return fichero.readLines()
        // ignoro la primera porque es el encabezado
        .drop(1)
        // separo por comas
        .map { linea -> linea.split(",") }
        // convierto a coche
        .map { columnas ->
            Coche(
                columnas[0].toInt(), // id
                columnas[1], // marca
                columnas[2].toDouble(), // precio
                LocalDate.parse(columnas[3]) // fechaLanzamiento
            )
        }

    // De esta manera lo procesamos línea a línea, como secuencia
    /*fichero.useLines { lines ->
        return lines
            // ignoro la primera porque es el encabezado
            .drop(1)
            // separo por comas
            .map { linea -> linea.split(",") }
            // convierto a coche
            .map { columnas ->
                Coche(
                    columnas[0].toInt(), // id
                    columnas[1], // marca
                    columnas[2].toDouble(), // precio
                    LocalDate.parse(columnas[3]) // fechaLanzamiento
                )
            }.toList()
    }*/
}

fun escribirCsv(coches: List<Coche>) {
    val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}coches.csv"
    val fichero = File(path)

    // Escribimos el encabezado, separados por comas
    fichero.writeText("id,marca,precio,fechaLanzamiento\n")
    coches.forEach {
        fichero.appendText("${it.id},${it.marca},${it.precio},${it.fechaLanzamiento}\n")
    }
}
