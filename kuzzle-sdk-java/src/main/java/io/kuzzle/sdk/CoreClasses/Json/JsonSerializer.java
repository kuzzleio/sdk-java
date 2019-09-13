package io.kuzzle.sdk.CoreClasses.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ConcurrentHashMap;

public class JsonSerializer {
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .disableHtmlEscaping()
                .registerTypeAdapter(
                        ConcurrentHashMap.class,
                        new ConcurrentHashMapTypeAdapter()
                )
                .create();
    }

    public static ConcurrentHashMap<String, Object> deserialize(String rawJson) {
        return gson.fromJson(rawJson, ConcurrentHashMap.class);
    }

    public static String serialize(ConcurrentHashMap<String, Object> map) {
        return gson.toJson(map, ConcurrentHashMap.class);
    }
}
