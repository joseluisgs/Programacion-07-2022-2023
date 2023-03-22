package models


import com.squareup.moshi.Json

data class Pokedex(
    @Json(name = "pokemon")
    val pokemon: List<Pokemon> = listOf()
)