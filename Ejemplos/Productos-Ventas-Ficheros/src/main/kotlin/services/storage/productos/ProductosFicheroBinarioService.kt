package services.storage.productos

import config.AppConfig
import models.Producto
import mu.KotlinLogging
import java.io.File
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

object ProductosFicheroBinarioService : ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.bin"


    override fun saveAll(items: List<Producto>) {
        logger.debug { "Guardando productos en fichero binario" }
        val file = File(localFile)
        file.outputStream().buffered().use {
            items.forEach { item ->
                it.write(item.id.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.nombre.toByteArray())
                it.write("\n".toByteArray())
                it.write(item.precio.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.cantidad.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.createdAt.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.updatedAt.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.disponible.toString().toByteArray())
                it.write("\n".toByteArray())
            }
        }
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "Cargando productos desde fichero de binario" }
        val file = File(localFile)
        val productos = mutableListOf<Producto>()
        // early return
        if (!file.exists()) return productos

        file.inputStream().buffered().use {
            while (it.available() > 0) {
                val idString = StringBuilder()
                var char = it.read().toChar()
                while (char != '\n') {
                    idString.append(char)
                    char = it.read().toChar()
                }
                val id = idString.toString().toInt()

                val nombreString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    nombreString.append(char)
                    char = it.read().toChar()
                }
                val nombre = nombreString.toString()

                val precioString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    precioString.append(char)
                    char = it.read().toChar()
                }
                val precio = precioString.toString().toDouble()

                val cantidadString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    cantidadString.append(char)
                    char = it.read().toChar()
                }
                val cantidad = cantidadString.toString().toInt()

                val createdAtString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    createdAtString.append(char)
                    char = it.read().toChar()
                }
                val createdAt = LocalDate.parse(createdAtString.toString())

                val updatedAtString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    updatedAtString.append(char)
                    char = it.read().toChar()
                }
                val updatedAt = LocalDate.parse(updatedAtString.toString())

                val disponibleString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    disponibleString.append(char)
                    char = it.read().toChar()
                }
                val disponible = disponibleString.toString().toBoolean()

                productos.add(Producto(id, nombre, precio, cantidad, createdAt, updatedAt, disponible))
            }
        }

        return productos
    }
}