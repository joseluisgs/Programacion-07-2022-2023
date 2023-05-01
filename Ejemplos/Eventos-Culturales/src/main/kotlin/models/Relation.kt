package models


import com.squareup.moshi.Json

data class Relation(
    @Json(name = "@id")
    val id: String = ""
)