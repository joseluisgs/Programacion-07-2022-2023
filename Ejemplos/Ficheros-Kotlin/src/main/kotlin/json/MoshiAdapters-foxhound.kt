package json

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class UuidAdapter : JsonAdapter<UUID>() {
    @FromJson
    override fun fromJson(reader: JsonReader): UUID? = UUID.fromString(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: UUID?) {
        writer.jsonValue(value.toString())
    }
}

class LocalDateAdapter : JsonAdapter<LocalDate>() {
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate? = LocalDate.parse(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.jsonValue(value.toString())
    }
}

class LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDateTime? = LocalDateTime.parse(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        writer.jsonValue(value.toString())
    }
}

inline fun <reified T> JsonAdapter<T>.toPrettyJson(value: T) = this.indent("  ").toJson(value)

object MoshiParser {
    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .add(LocalDateAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun newInstance(): Moshi = moshi
}
