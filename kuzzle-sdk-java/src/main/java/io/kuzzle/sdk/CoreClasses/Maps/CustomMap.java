package io.kuzzle.sdk.CoreClasses.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomMap extends ConcurrentHashMap<String, Object> {

    public static CustomMap getCustomMap(ConcurrentHashMap<String, Object> map) {
        if (map instanceof CustomMap) {
            return (CustomMap)map;
        }
        return new CustomMap(map);
    }

    public CustomMap() {
        super();
    }

    public CustomMap(ConcurrentHashMap<String, Object> map) {
        super(map);
    }

    public boolean isNull(String key) {
        return super.get(key) == null;
    }

    public boolean isString(String key) {
        return super.get(key) instanceof String;
    }

    public boolean isBoolean(String key) {
        return super.get(key) instanceof Boolean;
    }

    public boolean isNumber(String key) {
        return super.get(key) instanceof Number;
    }

    public boolean isArrayList(String key) {
        return super.get(key) instanceof ArrayList;
    }

    public boolean isMap(String key) {
        return super.get(key) instanceof ConcurrentHashMap;
    }

    public String getString(String key) {
        return isString(key)
                ? (String)super.get(key)
                : null;
    }

    public Boolean getBoolean(String key) {
        return isBoolean(key)
                ? (Boolean) super.get(key)
                : null;
    }

    public Number getNumber(String key) {
        return isNumber(key)
                ? (Number) super.get(key)
                : null;
    }

    public ArrayList getArrayList(String key) {
        return isArrayList(key)
                ? (ArrayList) super.get(key)
                : null;
    }

    public ConcurrentHashMap<String, Object> getMap(String key) {
        return isMap(key)
                ? (ConcurrentHashMap<String, Object>)super.get(key)
                : null;
    }

    public String optString(String key, String def) {
        return isString(key)
                ? (String)super.get(key)
                : def;
    }

    public Boolean optBoolean(String key, Boolean def) {
        return isBoolean(key)
                ? (Boolean) super.get(key)
                : def;
    }

    public Number optNumber(String key, Number def) {
        return isNumber(key)
                ? (Number) super.get(key)
                : def;
    }

    public ArrayList optArrayList(String key, ArrayList def) {
        return isArrayList(key)
                ? (ArrayList) super.get(key)
                : def;
    }

    public ConcurrentHashMap<String, Object> optMap(
            String key,
            ConcurrentHashMap<String, Object> def
    ) {
        return isMap(key)
                ? (ConcurrentHashMap<String, Object>)super.get(key)
                : def;
    }
}
