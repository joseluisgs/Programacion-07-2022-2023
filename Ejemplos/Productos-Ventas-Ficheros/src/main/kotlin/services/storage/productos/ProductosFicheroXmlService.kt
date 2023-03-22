package services.storage.productos

import config.AppConfig
import mappers.toDto
import mappers.toProductosList
import models.Producto
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

private val logger = KotlinLogging.logger {}

object ProductosFicheroXmlService : ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.xml"

    private val serializer = Persister()


    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero xml" }
        val file = File(localFile)
        serializer.write(items.toDto(), file)
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de xml" }
        val file = File(localFile)
        return serializer.read(dto.ProductosListDto::class.java, file).toProductosList()
    }
}