package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.time.LocalDate

@Root(name = "venta")
data class VentaDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: Int = 0,

    @field:Attribute(name = "user_id")
    @param:Attribute(name = "user_id")
    val userId: Int,

    @field:ElementList(name = "lineas", inline = true)
    @param:ElementList(name = "lineas", inline = true)
    val lineas: List<LineaVentaDto> = emptyList(),

    @field:Attribute(name = "created_at")
    @param:Attribute(name = "created_at")
    val createdAt: String = LocalDate.now().toString(),

    @field:Attribute(name = "updated_at")
    @param:Attribute(name = "updated_at")
    val updatedAt: String = LocalDate.now().toString()
)

@Root(name = "ventas")
data class VentasListDto(
    @field:ElementList(name = "ventas", inline = true)
    @param:ElementList(name = "ventas", inline = true)
    val ventas: List<VentaDto>
)