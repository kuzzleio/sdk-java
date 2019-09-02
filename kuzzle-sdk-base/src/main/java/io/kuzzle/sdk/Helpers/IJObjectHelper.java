package io.kuzzle.sdk.Helpers;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;

public class IJObjectHelper {
    private static IJObject JsonObject;

    /** Set the IJObject that is going to be used to instantiate new IJObject or parse Json.
     * @param jobject
     * @param <T> The json object of the Json library you want to use.
     */
    public static <T> void init(IJObject<T> jobject) {
        JsonObject = jobject;
    }


    /** Creates a new instance of IJObject.
     * @param <T> The json object of the Json library you want to use.
     * @return A new instance of IJObject.
     */
    public static <T> IJObject<T> newIJObject() {
        return JsonObject.newIJObject();
    }

    /** Creates a new instance of IJObject.
     * @param json A Json Object.
     * @param <T> The json object of the Json library you want to use.
     * @return A new instance of IJObject.
     */
    public static <T> IJObject<T> newIJObject(T json) {
        return JsonObject.newIJObject(json);
    }


    /** Parse a Json String and create a new instance of IJObject.
     * @param json A Json String.
     * @param <T> The json object of the Json library you want to use.
     * @return A new instance of IJObject created from the Json String.
     */
    public static <T> IJObject<T> parse(String json) {
        return JsonObject.parse(json);
    }
}
