package io.kuzzle.sdk.CoreClasses.Json;

/**
 * @param <T> The json object of the Json library you want to use.
 */
public abstract class JsonSerializable<T> {
    public abstract void fromIJObject(IJObject<T> obj);
    public abstract IJObject<T> toIJObject();

    @Override
    public String toString() {
        return toIJObject().toJsonString();
    }
}
