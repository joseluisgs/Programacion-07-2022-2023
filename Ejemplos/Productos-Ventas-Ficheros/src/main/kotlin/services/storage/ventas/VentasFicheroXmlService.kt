package services.storage.ventas

import config.AppConfig
import mappers.toDto
import mappers.toVentasList
import models.Venta
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

private val logger = KotlinLogging.logger {}

object VentasFicheroXmlService : VentasStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}ventas.xml"

    private val serializer = Persister()


    override fun saveAll(items: List<Venta>) {
        logger.debug { "Guardando ventas en fichero xml" }

        val file = File(localFile)
        serializer.write(items.toDto(), file)
    }

    override fun loadAll(): List<Venta> {
        logger.debug { "Cargando ventas desde fichero de xml" }
        val file = File(localFile)
        return serializer.read(dto.VentasListDto::class.java, file).toVentasList()
    }
}