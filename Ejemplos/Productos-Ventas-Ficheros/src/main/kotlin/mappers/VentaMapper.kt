package mappers

import dto.LineaVentaDto
import dto.VentaDto
import dto.VentasListDto
import models.LineaVenta
import models.Venta
import java.time.LocalDate

fun LineaVenta.toLineaVentaDto() = LineaVentaDto(
    idVenta = this.idVenta,
    idLineaVenta = this.idLineaVenta,
    idProducto = this.idProducto,
    cantidad = this.cantidad,
    precioProducto = this.precioProducto
)

fun LineaVentaDto.toLineaVenta() = LineaVenta(
    idVenta = this.idVenta,
    idLineaVenta = this.idLineaVenta,
    idProducto = this.idProducto,
    cantidad = this.cantidad,
    precioProducto = this.precioProducto
)

fun Venta.toDto() = VentaDto(
    id = this.id,
    userId = this.userId,
    lineas = this.lineas.map { it.toLineaVentaDto() }.toList(),
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString(),
)

fun VentaDto.toVenta() = Venta(
    id = this.id,
    userId = this.userId,
    lineas = this.lineas.map { it.toLineaVenta() }.toMutableList(),
    createdAt = LocalDate.parse(this.createdAt),
    updatedAt = LocalDate.parse(this.updatedAt),
)

fun List<Venta>.toDto() = VentasListDto(
    ventas = map { it.toDto() }
)

fun VentasListDto.toVentasList() = ventas.map { it.toVenta() }