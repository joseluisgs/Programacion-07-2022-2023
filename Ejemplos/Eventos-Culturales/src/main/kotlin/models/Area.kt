package models


import com.squareup.moshi.Json

data class Area(
    @Json(name = "@id")
    val id: String = "",
    @Json(name = "locality")
    val locality: String = "",
    @Json(name = "postal-code")
    val postalCode: String = "",
    @Json(name = "street-address")
    val streetAddress: String = ""
)