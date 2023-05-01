package models


import com.squareup.moshi.Json

data class Evento(
    @Json(name = "@context")
    val context: Context = Context(),
    @Json(name = "@graph")
    val eventoData: List<EventoData> = listOf()
)