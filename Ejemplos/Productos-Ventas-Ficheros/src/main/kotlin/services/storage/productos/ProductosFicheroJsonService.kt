package services.storage.productos

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import models.Producto
import mu.KotlinLogging
import utils.LocalDateAdapter
import utils.LocalDateTimeAdapter
import utils.UuidAdapter
import utils.toPrettyJson
import java.io.File

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi // Para usar el método DE adapter con enlazado de Kotlin
object ProductosFicheroJsonService : ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.json"

    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .add(LocalDateTimeAdapter())
        .add(LocalDateAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<List<Producto>>()


    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero json" }
        val file = File(localFile)
        file.writeText(jsonAdapter.toPrettyJson(items))
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de json" }
        val file = File(localFile)
        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}