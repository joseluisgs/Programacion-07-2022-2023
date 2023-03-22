package models


import com.squareup.moshi.Json

data class Pokemon(
    @Json(name = "avg_spawns")
    val avgSpawns: Double = 0.0,
    @Json(name = "candy")
    val candy: String = "",
    @Json(name = "candy_count")
    val candyCount: Int? = null,
    @Json(name = "egg")
    val egg: String = "",
    @Json(name = "height")
    val height: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "img")
    val img: String = "",
    @Json(name = "multipliers")
    val multipliers: List<Double>? = null,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "next_evolution")
    val nextEvolution: List<NextEvolution>? = null,
    @Json(name = "num")
    val num: String = "",
    @Json(name = "prev_evolution")
    val prevEvolution: List<PrevEvolution>? = null,
    @Json(name = "spawn_chance")
    val spawnChance: Double = 0.0,
    @Json(name = "spawn_time")
    val spawnTime: String = "",
    @Json(name = "type")
    val type: List<String> = listOf(),
    @Json(name = "weaknesses")
    val weaknesses: List<String> = listOf(),
    @Json(name = "weight")
    val weight: String = ""
)