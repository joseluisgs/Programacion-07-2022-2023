package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "linea_venta")
data class LineaVentaDto(

    @field:Attribute(name = "id_venta")
    @param:Attribute(name = "id_venta")
    val idVenta: Int,

    @field:Attribute(name = "id_linea_venta")
    @param:Attribute(name = "id_linea_venta")
    val idLineaVenta: Int,

    @field:Attribute(name = "id_producto")
    @param:Attribute(name = "id_producto")
    val idProducto: Int,

    @field:Attribute(name = "cantidad")
    @param:Attribute(name = "cantidad")
    val cantidad: Int,

    @field:Attribute(name = "precio_producto")
    @param:Attribute(name = "precio_producto")
    val precioProducto: Double
)