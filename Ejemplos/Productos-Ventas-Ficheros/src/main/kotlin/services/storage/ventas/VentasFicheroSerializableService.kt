package services.storage.ventas

import config.AppConfig
import models.Venta
import mu.KotlinLogging
import java.io.*

private val logger = KotlinLogging.logger {}

object VentasFicheroSerializableService : VentasStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}ventas.ser"


    override fun saveAll(items: List<Venta>) {
        logger.debug { "Guardando ventas en fichero serializable" }
        val file = File(localFile)

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            // Podemos escribir cualquier objeto serializable, ya sea un objeto o una colección
            it.writeObject(items)
        }
    }

    override fun loadAll(): List<Venta> {
        logger.debug { "Cargando ventas desde fichero de serializable" }
        val file = File(localFile)
        var ventas = mutableListOf<Venta>()
        // early return
        if (!file.exists()) return ventas
        val input = ObjectInputStream(FileInputStream(file)).use {
            // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección
            ventas = it.readObject() as MutableList<Venta>
        }

        return ventas
    }
}