package io.kuzzle.sdk.CoreClasses.Json;

public interface JsonSerializable<T> {
    public void fromIJObject(IJObject<T> obj);
    public IJObject<T> toIJObject();
}
