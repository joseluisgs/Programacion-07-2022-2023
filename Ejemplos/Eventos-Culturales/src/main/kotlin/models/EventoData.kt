package models


import com.squareup.moshi.Json

data class EventoData(
    @Json(name = "@id")
    val idUrl: String = "",
    @Json(name = "@type")
    val typeUrl: String? = null,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "free")
    val free: Int = 0,
    @Json(name = "price")
    val price: String = "",
    @Json(name = "dtstart")
    val dtstart: String = "",
    @Json(name = "dtend")
    val dtend: String = "",
    @Json(name = "time")
    val time: String = "",
    @Json(name = "excluded-days")
    val excludedDays: String = "",
    @Json(name = "uid")
    val uid: String = "",
    @Json(name = "link")
    val link: String = "",
    @Json(name = "event-location")
    val eventLocation: String = "",
    @Json(name = "references")
    val references: References = References(),
    @Json(name = "relation")
    val relation: Relation? = null,
    @Json(name = "address")
    val address: Address? = null,
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "organization")
    val organization: Organization? = null,
    @Json(name = "audience")
    val audience: String? = null,
    @Json(name = "recurrence")
    val recurrence: Recurrence? = null
)