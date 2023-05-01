package es.joseluisgs.json.utils;

import com.squareup.moshi.*;

import java.io.IOException;
import java.util.UUID;

public class UuidAdapter extends JsonAdapter<UUID> {
    @Override
    @FromJson
    public UUID fromJson(JsonReader jsonReader) throws IOException {
        return UUID.fromString(jsonReader.nextString());
    }


    @Override
    @ToJson
    public void toJson(JsonWriter jsonWriter, UUID uuid) throws IOException {
        jsonWriter.value(uuid.toString());
    }

}