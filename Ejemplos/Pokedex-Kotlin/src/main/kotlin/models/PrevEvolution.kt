package models


import com.squareup.moshi.Json

data class PrevEvolution(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "num")
    val num: String = ""
)