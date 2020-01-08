package io.kuzzle.sdk.CoreClasses.Maps;

import java.util.concurrent.ConcurrentHashMap;

public interface Serializable {
  void fromMap(ConcurrentHashMap<String, Object> map) throws Exception;

  ConcurrentHashMap<String, Object> toMap() throws Exception;
}
