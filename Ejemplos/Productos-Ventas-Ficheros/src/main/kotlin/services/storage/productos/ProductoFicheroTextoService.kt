package services.storage.productos

import models.Producto
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

object ProductoFicheroTextoService : ProductosStorageService {
    val localPath = "${System.getProperty("user.dir")}${File.separator}data"
    val localFile = "${localPath}${File.separator}productos.txt"

    init {
        // Crear el directorio data si no existe
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(localPath))
    }

    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero de texto" }
        val file = File(localFile)
        file.writeText("")
        items.forEach {
            file.appendText(it.id.toString() + "\n")
            file.appendText(it.nombre + "\n")
            file.appendText(it.precio.toString() + "\n")
            file.appendText(it.cantidad.toString() + "\n")
            file.appendText(it.createdAt.toString() + "\n")
            file.appendText(it.updatedAt.toString() + "\n")
            file.appendText(it.disponible.toString() + "\n")
        }
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de texto" }
        val file = File(localFile)
        val productos = mutableListOf<Producto>()
        // early return
        if (!file.exists()) return productos

        file.bufferedReader().use {
            while (it.ready()) {
                val id = it.readLine().toInt()
                val nombre = it.readLine().trim()
                val precio = it.readLine().toDouble()
                val cantidad = it.readLine().toInt()
                val createdAt = LocalDate.parse(it.readLine().trim())
                val updatedAt = LocalDate.parse(it.readLine().trim())
                val disponible = it.readLine().toBoolean()
                productos.add(Producto(id, nombre, precio, cantidad, createdAt, updatedAt, disponible))
            }
        }
        return productos
    }
}