package mappers

import dto.ProductoDto
import dto.ProductosListDto
import models.Producto
import java.time.LocalDate

fun ProductoDto.toProducto() = Producto(
    id = id,
    nombre = nombre,
    precio = precio,
    cantidad = cantidad,
    createdAt = LocalDate.parse(createdAt),
    updatedAt = LocalDate.parse(updatedAt),
    disponible = disponible,
)

fun Producto.toProductoDto() = ProductoDto(
    id = id,
    nombre = nombre,
    precio = precio,
    cantidad = cantidad,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString(),
    disponible = disponible,
)

fun List<Producto>.toDto() = ProductosListDto(
    productos = map { it.toProductoDto() }
)

fun ProductosListDto.toProductosList() = productos.map { it.toProducto() }
