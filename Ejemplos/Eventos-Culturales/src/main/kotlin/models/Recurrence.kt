package models


import com.squareup.moshi.Json

data class Recurrence(
    @Json(name = "days")
    val days: String = "",
    @Json(name = "frequency")
    val frequency: String = "",
    @Json(name = "interval")
    val interval: Int = 0
)