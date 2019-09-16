package io.kuzzle.sdk.CoreClasses.Maps;

import java.util.concurrent.ConcurrentHashMap;

public interface Serializable {
    public void fromMap(ConcurrentHashMap<String, Object> map);
    public ConcurrentHashMap<String, Object> toMap();
}
