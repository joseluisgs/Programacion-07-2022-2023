package services.storage.productos

import models.Producto
import mu.KotlinLogging
import java.io.File
import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

object ProductoFicheroAleatorioService : ProductosStorageService {
    val localPath = "${System.getProperty("user.dir")}${File.separator}data"
    val localFile = "${localPath}${File.separator}productos.dat"

    init {
        // Crear el directorio data si no existe
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(localPath))
    }

    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero de aleatorio" }
        val file = RandomAccessFile(localFile, "rw")
        items.forEach {
            file.writeInt(it.id)
            file.writeUTF(it.nombre)
            file.writeDouble(it.precio)
            file.writeInt(it.cantidad)
            file.writeUTF(it.createdAt.toString())
            file.writeUTF(it.updatedAt.toString())
            file.writeBoolean(it.disponible)
        }
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de aleatorio" }
        val file = RandomAccessFile(localFile, "r")
        val productos = mutableListOf<Producto>()
        // early return
        if (!File(localFile).exists()) return productos
        if (file.length() == 0L) return productos

        while (file.filePointer < file.length()) {
            val id = file.readInt()
            val nombre = file.readUTF()
            val precio = file.readDouble()
            val cantidad = file.readInt()
            val createdAt = LocalDate.parse(file.readUTF())
            val updatedAt = LocalDate.parse(file.readUTF())
            val disponible = file.readBoolean()
            productos.add(Producto(id, nombre, precio, cantidad, createdAt, updatedAt, disponible))
        }
        return productos
    }
}