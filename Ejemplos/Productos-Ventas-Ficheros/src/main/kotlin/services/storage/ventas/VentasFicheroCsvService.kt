package services.storage.ventas

import config.AppConfig
import models.LineaVenta
import models.Venta
import mu.KotlinLogging
import java.io.File
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

object VentasFicheroCsvService : VentasStorageService {

    val localFile = "${AppConfig.APP_DATA}${File.separator}ventas.csv"


    override fun saveAll(items: List<Venta>) {
        logger.debug { "Guardando ventas en fichero csv" }
        val file = File(localFile)
        // Escribir el encabezado
        file.writeText("id,user_id,lineas,createdAt,updatedAt,total,total_items" + "\n")
        items.forEach {
            file.appendText("${it.id},${it.userId},${fromLineaVentasListToCsvColumn(it.lineas)},${it.createdAt},${it.updatedAt},${it.total},${it.totalItems}" + "\n")
        }
    }

    private fun fromLineaVentasListToCsvColumn(lineas: List<LineaVenta>): String {
        return lineas.joinToString(separator = "|", prefix = "[", postfix = "]") {
            "${it.idVenta};${it.idLineaVenta};${it.idProducto};${it.cantidad};${it.precioProducto};${it.total}"
        }
    }

    override fun loadAll(): List<Venta> {
        logger.debug { "Cargando ventas desde fichero csv" }
        val file = File(localFile)
        // early return
        if (!file.exists()) return emptyList()
        // Leer el fichero
        return file.readLines()
            .drop(1)
            .map { linea -> linea.split(",") }
            .map { campos -> campos.map { it.trim() } } // trim() elimina espacios en blanco
            .map { campos ->
                Venta(
                    id = campos[0].toInt(),
                    userId = campos[1].toInt(),
                    lineas = fromCsvColumnToLineaVentaList(campos[2]).toMutableList(),
                    createdAt = LocalDate.parse(campos[3]),
                    updatedAt = LocalDate.parse(campos[4]),
                )
            }
    }

    private fun fromCsvColumnToLineaVentaList(columna: String): List<LineaVenta> {
        return columna
            .replace("[", "")
            .replace("]", "")
            .split("|")
            .map { it.split(";") }
            .map {
                LineaVenta(
                    idVenta = it[0].toInt(),
                    idLineaVenta = it[1].toInt(),
                    idProducto = it[2].toInt(),
                    cantidad = it[3].toInt(),
                    precioProducto = it[4].toDouble(),
                )
            }

    }
}