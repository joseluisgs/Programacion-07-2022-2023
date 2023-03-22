package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.time.LocalDate

@Root(name = "producto")
data class ProductoDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: Int = 0,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String = "",

    @field:Element(name = "precio")
    @param:Element(name = "precio")
    val precio: Double = 0.0,

    @field:Element(name = "cantidad")
    @param:Element(name = "cantidad")
    val cantidad: Int = 0,

    @field:Element(name = "created_at")
    @param:Element(name = "created_at")
    val createdAt: String = LocalDate.now().toString(),

    @field:Element(name = "updated_at")
    @param:Element(name = "updated_at")
    val updatedAt: String = LocalDate.now().toString(),

    @field:Attribute(name = "disponible")
    @param:Attribute(name = "disponible")
    val disponible: Boolean = true,
)

@Root(name = "productos_list")
data class ProductosListDto(
    @field:ElementList(name = "productos", inline = true)
    @param:ElementList(name = "productos", inline = true)
    val productos: List<ProductoDto>
)