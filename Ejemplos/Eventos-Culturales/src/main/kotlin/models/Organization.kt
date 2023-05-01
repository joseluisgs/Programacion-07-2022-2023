package models


import com.squareup.moshi.Json

data class Organization(
    @Json(name = "organization-name")
    val organizationName: String = "",
    @Json(name = "accesibility")
    val accesibility: String = ""
)