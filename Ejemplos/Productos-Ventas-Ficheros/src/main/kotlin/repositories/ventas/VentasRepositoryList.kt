package repositories.ventas

import models.Venta
import mu.KotlinLogging
import services.storage.ventas.VentasStorageService

private val logger = KotlinLogging.logger {}

class VentasRepositoryMap(
    private val storageService: VentasStorageService
) : VentasRepository {
    private val ventas = mutableListOf<Venta>()
    override fun getNewId(): Int {
        logger.debug { "getNewId" }
        val lastId = ventas.maxOfOrNull { it.id } ?: 0
        return lastId + 1
    }

    override fun findAll(): List<Venta> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Venta? {
        logger.debug { "findById(${id})" }

        // leer del Storage
        loadAll()

        return ventas.find { it.id == id }
    }

    override fun save(entity: Venta): Venta {
        logger.debug { "save: $entity" }
        ventas.add(entity)

        // Guardar los cambios
        saveAll()

        return entity
    }

    override fun update(entity: Venta): Venta? {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): Venta? {
        TODO("Not yet implemented")
    }

    private fun saveAll() {
        logger.debug { "Ventas saveAll to Storage" }
        storageService.saveAll(ventas)
    }

    private fun loadAll() {
        logger.debug { "Ventas loadAll from Storage" }
        ventas.clear()
        storageService.loadAll().forEach {
            ventas.add(it)
        }
    }

}