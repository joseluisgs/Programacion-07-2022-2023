package models


import com.squareup.moshi.Json

data class District(
    @Json(name = "@id")
    val id: String = ""
)