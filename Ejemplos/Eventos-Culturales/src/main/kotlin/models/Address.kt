package models


import com.squareup.moshi.Json

data class Address(
    @Json(name = "district")
    val district: District = District(),
    @Json(name = "area")
    val area: Area? = null
)