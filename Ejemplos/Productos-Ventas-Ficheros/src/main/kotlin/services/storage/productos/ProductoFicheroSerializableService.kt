package services.storage.productos

import models.Producto
import mu.KotlinLogging
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

private val logger = KotlinLogging.logger {}


object ProductoFicheroSerializableService : ProductosStorageService {
    val localPath = "${System.getProperty("user.dir")}${File.separator}data"
    val localFile = "${localPath}${File.separator}productos.ser"

    init {
        // Crear el directorio data si no existe
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(localPath))
    }

    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero serializable" }
        val file = File(localFile)

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            // Podemos escribir cualquier objeto serializable, ya sea un objeto o una colección
            it.writeObject(items)
        }
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de serializable" }
        val file = File(localFile)
        var productos = mutableListOf<Producto>()
        // early return
        if (!file.exists()) return productos
        val input = ObjectInputStream(FileInputStream(file)).use {
            // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección
            productos = it.readObject() as MutableList<Producto>
        }

        return productos
    }
}