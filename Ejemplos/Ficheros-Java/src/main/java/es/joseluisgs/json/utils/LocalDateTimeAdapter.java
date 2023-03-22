package es.joseluisgs.json.utils;

import com.squareup.moshi.*;

import java.io.IOException;
import java.time.LocalDateTime;


public class LocalDateTimeAdapter extends JsonAdapter<LocalDateTime> {
    @FromJson
    @Override
    public LocalDateTime fromJson(JsonReader jsonReader) throws IOException {
        return LocalDateTime.parse(jsonReader.readJsonValue().toString());
    }

    @ToJson
    @Override
    public void toJson(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.toString());
    }
}