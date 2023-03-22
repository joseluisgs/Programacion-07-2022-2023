package repositories.productos

import models.Producto
import mu.KotlinLogging
import services.storage.productos.ProductosStorageService
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

class ProductosRepositoryMap(
    private val storageService: ProductosStorageService
) : ProductosRepository {

    // almacen de productos en memoria
    private val almacen = mutableMapOf<Int, Producto>()


    override fun findAllByDisponible(disponible: Boolean): List<Producto> {
        logger.debug { "findAllByDisponible: $disponible" }

        // leer del Storage
        loadAll()

        return almacen.values.filter { it.disponible == disponible }
    }

    override fun findByNombre(nombre: String): List<Producto> {
        logger.debug { "findByNombre: $nombre" }

        // leer del Storage
        loadAll()

        return almacen.values.filter { it.nombre.lowercase().contains(nombre.lowercase()) }
    }

    override fun findAll(): List<Producto> {
        logger.debug { "findAll" }

        // leer del Storage
        loadAll()

        return almacen.values.toList()
    }

    override fun findById(id: Int): Producto? {
        logger.debug { "findById: $id" }

        // leer del Storage
        loadAll()

        return almacen[id]
    }

    override fun save(entity: Producto): Producto {
        logger.debug { "save: $entity" }
        // Obtener primero el ultimo id
        val lastId = (almacen.keys.maxOrNull() ?: 0) + 1
        // Crear un nuevo producto con el id
        val newProducto = entity.copy(id = lastId, createdAt = LocalDate.now(), updatedAt = LocalDate.now())
        // Guardar el producto en el almacen
        almacen[newProducto.id] = newProducto
        // Retornar el producto
        logger.debug { "newProducto: $newProducto" }

        // Guardar los cambios
        saveAll()

        return newProducto
    }

    override fun update(entity: Producto): Producto? {
        logger.debug { "update: $entity" }
        // Si no es nulo, actualizar el producto
        almacen[entity.id]?.let {
            logger.debug { "producto existe y actualizando valores: $entity" }
            val updatedProducto = entity.copy(updatedAt = LocalDate.now())
            almacen[updatedProducto.id] = updatedProducto

            // Guardar los cambios
            saveAll()

            return updatedProducto
        } ?: return null
    }

    override fun deleteById(id: Int): Producto? {
        logger.debug { "deleteById: $id" }
        val producto = almacen.remove(id)

        // Guardar los cambios
        saveAll()

        return producto
    }

    private fun saveAll() {
        logger.debug { "Productos saveAll to Storage" }
        storageService.saveAll(almacen.values.toList())
    }

    private fun loadAll() {
        logger.debug { "Productos loadAll from Storage" }
        almacen.clear()
        storageService.loadAll().forEach {
            almacen[it.id] = it
        }
    }
}