package io.kuzzle.sdk.Helpers;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;

public class IJObjectHelper {
    private static IJObject JsonObject;

    public static <T> void init(IJObject<T> jobject) {
        JsonObject = jobject;
    }

    public static <T> IJObject<T> newIJObject() {
        return JsonObject.newIJObject();
    }

    public static <T> IJObject<T> newIJObject(T json) {
        return JsonObject.newIJObject(json);
    }

    public static <T> IJObject<T> parse(String json) {
        return JsonObject.parse(json);
    }
}
