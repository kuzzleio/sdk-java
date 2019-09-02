package io.kuzzle.sdk.CoreClasses.Json;

/**
 * @param <T> The json object of the Json library you want to use.
 */
public interface JsonSerializable<T> {
    public void fromIJObject(IJObject<T> obj);
    public IJObject<T> toIJObject();
}
