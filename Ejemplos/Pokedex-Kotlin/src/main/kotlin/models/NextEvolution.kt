package models


import com.squareup.moshi.Json

data class NextEvolution(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "num")
    val num: String = ""
)