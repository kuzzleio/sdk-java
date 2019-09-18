package io.kuzzle.sdk.Helpers;

public class Default {

    /** Return the object or its default value in the case of the object is null.
     * @param obj An object.
     * @param defaultValue A default value in case your object is null.
     * @param <T> Object class.
     * @return The object or the specified default value in case the object is null.
     */
    public static <T> T notNull(T obj, T defaultValue) {
        return obj != null ? obj : defaultValue;
    }
}
