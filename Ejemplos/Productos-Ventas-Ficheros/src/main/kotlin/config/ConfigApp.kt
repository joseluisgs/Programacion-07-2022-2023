package config

import mu.KotlinLogging
import java.util.*


private val logger = KotlinLogging.logger {}

object ConfigApp {
    val APP_NAME = "Kotlin Productos Ventas"
    val APP_VERSION = "1.0.0"
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String

    init {
        loadProperties()
    }

    private fun loadProperties() {
        logger.debug { "Cargando configuración de la aplicación" }
        val properties = Properties()
        properties.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))
        /*
        properties.forEach { (key, value) ->
            logger.debug { "Configuración: $key = $value" }
        }
        */
        APP_AUTHOR = properties.getProperty("app.author") ?: "1DAM"
        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"

        logger.debug { "Configuración: app.author = $APP_AUTHOR" }
        logger.debug { "Configuración: app.storage.dir = $APP_DATA" }
    }

}