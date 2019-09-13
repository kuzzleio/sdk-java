package io.kuzzle.runner.Helpers;

import io.kuzzle.runner.CoreClasses.GObject;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;

public class IJObjectHelper<T> {

    private static IJObject jsonConstructor = new GObject();

    public static <T> IJObject<T> newIJObject() {
        return jsonConstructor.newIJObject();
    }

    public static <T> IJObject<T> newIJObject(T json) {
        return jsonConstructor.newIJObject(json);
    }

    public static <T> IJObject<T> parse(String json) {
        return jsonConstructor.parse(json);
    }

}
