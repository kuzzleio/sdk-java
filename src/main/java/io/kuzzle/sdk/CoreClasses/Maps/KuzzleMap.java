package io.kuzzle.sdk.CoreClasses.Maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * CustomMap is a Class that extends ConcurrentHashMap to be ThreadSafe and that
 * has the purpose of giving a wrapper on top of ConcurrentHashMap to easily
 * manipulate them.
 */
public class KuzzleMap extends ConcurrentHashMap<String, Object> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -3027862451021177820L;

  /**
   * Convert à ConcurrentHashMap<String, Object> to a CustomMap
   * 
   * @param map ConcurrentHashMap<String, Object> representing JSON.
   * @return a CustomMap instance
   */
  public static KuzzleMap from(ConcurrentHashMap<String, Object> map) {
    if (map == null) {
      return null;
    }
    if (map instanceof KuzzleMap) {
      return (KuzzleMap) map;
    }
    return new KuzzleMap(map);
  }

  /**
   * Create a new instance of CustomMap
   */
  public KuzzleMap() {
    super();
  }

  /**
   * Create a new instance of CustomMap from a ConcurrentHashMap<String, Object>.
   * 
   * @param map ConcurrentHashMap<String, Object> representing JSON.
   */
  public KuzzleMap(ConcurrentHashMap<String, Object> map) {
    super();
    Iterator<Entry<String, Object>> it = map.entrySet().iterator();

    while (it.hasNext()) {
      Entry<String, Object> entry = it.next();
      this.put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public KuzzleMap put(String s, Object o) {
    if (o != null) {
      super.put(s, o);
    } else {
      super.put(s, new Null());
    }
    
    return this;
  }

  @Override
  public Object get(Object key) {
    Object value = super.get(key);
    if (value instanceof Null) {
      return null;
    }
    return value;
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    Set<Entry<String, Object>> entrySet = super.entrySet();
    return entrySet.parallelStream().map((Entry<String, Object> entry) -> {
      if (entry.getValue() instanceof Null) {
        return new KuzzleMapEntry(entry.getKey(), entry.getValue(), this);
      }
      return entry;
    }).collect(Collectors.toSet());
  }

  /**
   * Check whether the key value is null or not.
   * 
   * @param key a String representing the key.
   * @return true if the value is null.
   */
  public boolean isNull(String key) {
    return super.get(key) instanceof Null;
  }

  /**
   * Check whether the key value is a String or not.
   * 
   * @param key a String representing the key.
   * @return true if the key is a String.
   */
  public boolean isString(String key) {
    return super.get(key) instanceof String;
  }

  /**
   * Check whether the key value is a Boolean or not.
   * 
   * @param key a String representing the key.
   * @return true if the key is a Boolean.
   */
  public boolean isBoolean(String key) {
    return super.get(key) instanceof Boolean;
  }

  /**
   * Check whether the key value is a Number or not.
   * 
   * @param key a String representing the key.
   * @return true if the key is a Number.
   */
  public boolean isNumber(String key) {
    return super.get(key) instanceof Number;
  }

  /**
   * Check whether the key value is an ArrayList or not.
   * 
   * @param key a String representing the key.
   * @return true if the key is an ArrayList.
   */
  public boolean isArrayList(String key) {
    return super.get(key) instanceof ArrayList;
  }

  /**
   * Check whether the key value is a ConcurrentHashMap or not.
   * 
   * @param key a String representing the key.
   * @return true if the key is a ConcurrentHashMap.
   */
  public boolean isMap(String key) {
    return super.get(key) instanceof ConcurrentHashMap;
  }

  /**
   * Return the specified key value or null if the value is not a String.
   * 
   * @param key a String representing the key.
   * @return The String at the key or null
   */
  public String getString(String key) {
    return isString(key) ? (String) super.get(key) : null;
  }

  /**
   * Return the specified key value or null if the value is not a Boolean.
   * 
   * @param key a String representing the key.
   * @return The Boolean at the key or null
   */
  public Boolean getBoolean(String key) {
    return isBoolean(key) ? (Boolean) super.get(key) : null;
  }

  /**
   * Return the specified key value or null if the value is not a Number.
   * 
   * @param key a String representing the key.
   * @return The Number at the key or null
   */
  public Number getNumber(String key) {
    return isNumber(key) ? (Number) super.get(key) : null;
  }

  /**
   * Return the specified key value or null if the value is not an ArrayList.
   * 
   * @param key a String representing the key.
   * @return The ArrayList at the key or null
   */
  public ArrayList getArrayList(String key) {
    return isArrayList(key) ? (ArrayList) super.get(key) : null;
  }

  /**
   * Return the specified key value or null if the value is not a
   * ConcurrentHashMap.
   * 
   * @param key a String representing the key.
   * @return The ConcurrentHashMap at the key or null
   */
  public KuzzleMap getMap(String key) {
    return isMap(key) ? KuzzleMap.from((ConcurrentHashMap<String, Object>) super.get(key)) : null;
  }

  /**
   * Return the specified key value or the def value if the value is nul or not a
   * String.
   * 
   * @param key a String representing the key.
   * @return The String at the key or def value
   */
  public String optString(String key, String def) {
    return isString(key) ? (String) super.get(key) : def;
  }

  /**
   * Return the specified key value or the def value if the value is nul or not a
   * Boolean.
   * 
   * @param key a String representing the key.
   * @return The Boolean at the key or def value
   */
  public Boolean optBoolean(String key, Boolean def) {
    return isBoolean(key) ? (Boolean) super.get(key) : def;
  }

  /**
   * Return the specified key value or the def value if the value is nul or not a
   * Number.
   * 
   * @param key a String representing the key.
   * @return The Number at the key or def value
   */
  public Number optNumber(String key, Number def) {
    return isNumber(key) ? (Number) super.get(key) : def;
  }

  /**
   * Return the specified key value or the def value if the value is nul or not an
   * ArrayList.
   * 
   * @param key a String representing the key.
   * @return The ArrayList at the key or def value
   */
  public ArrayList optArrayList(String key, ArrayList def) {
    return isArrayList(key) ? (ArrayList) super.get(key) : def;
  }

  /**
   * Return the specified key value or the def value if the value is nul or not a
   * ConcurrentHashMap.
   * 
   * @param key a String representing the key.
   * @return The ConcurrentHashMap at the key or def value
   */
  public KuzzleMap optMap(String key, ConcurrentHashMap<String, Object> def) {
    return isMap(key) ? KuzzleMap.from((ConcurrentHashMap<String, Object>) super.get(key)) : KuzzleMap.from(def);
  }
}
