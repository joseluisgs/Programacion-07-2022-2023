package models


import com.squareup.moshi.Json

data class References(
    @Json(name = "@id")
    val id: String = ""
)