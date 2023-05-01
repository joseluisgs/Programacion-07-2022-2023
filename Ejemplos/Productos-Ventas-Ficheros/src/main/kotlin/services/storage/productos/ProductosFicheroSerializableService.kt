package services.storage.productos

import config.AppConfig
import models.Producto
import mu.KotlinLogging
import java.io.*

private val logger = KotlinLogging.logger {}


object ProductosFicheroSerializableService : ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.ser"


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