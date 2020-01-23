package io.kuzzle.sdk.CoreClasses.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KuzzleMapEntry implements Map.Entry<String, Object> {
  final String key;
  Object value;
  final ConcurrentHashMap<String, Object> map;

  public KuzzleMapEntry(String key, Object value, ConcurrentHashMap<String, Object> map) {
    this.key = key;
    this.value = value;
    this.map = map;
  }

  public String getKey() {
    return this.key;
  }

  public Object getValue() {
    if (this.value instanceof Null) {
      return null;
    }
    return this.value;
  }

  public int hashCode() {
    return this.key.hashCode() ^ this.value.hashCode();
  }

  public String toString() {
    return this.key + "=" + this.value;
  }

  public boolean equals(Object object) {
    Object key;
    Object value;
    Map.Entry entry;

    if (!(object instanceof Map.Entry)) {
      return false;
    }

    entry = (Map.Entry) object;
    key = entry.getKey();
    value = entry.getValue();
    return key != null && (value == this.value || (value != null && value.equals(this.value)));
  }

  public Object setValue(Object value) {
    if (value == null) {
      Object oldValue = this.value;
      this.value = new Null();
      this.map.put(this.key, this.value);
      return oldValue;
    } else {
      Object oldValue = this.value;
      this.value = value;
      this.map.put(this.key, value);
      return oldValue;
    }
  }
}
