package io.kuzzle.sdk.Helpers;

public class Default {
    public static <T> T notNull(T obj, T defaultValue) {
        return obj != null ? obj : defaultValue;
    }
}
