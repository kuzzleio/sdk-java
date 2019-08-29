package io.kuzzle.sdk.CoreClasses.Json;

public interface IJObject<T> {

    public void put(String property, Byte value);
    public void put(String property, Short value);
    public void put(String property, Integer value);
    public void put(String property, Long value);
    public void put(String property, String value);
    public void put(String property, Double value);
    public void put(String property, Float value);
    public void put(String property, Boolean value);
    public void put(String property, Character value);
    public void put(String property, IJObject<T> value);
    public void put(String property, Object value);

    public Byte getByte(String property);
    public Short getShort(String property);
    public Integer getInteger(String property);
    public Long getLong(String property);
    public Double getDouble(String property);
    public Float getFloat(String property);
    public String  getString(String property);
    public Boolean getBoolean(String property);
    public Character getCharacter(String property);
    public IJObject<T> getIJObject(String property);
    public Object get(String property);

    public byte optByte(String property, byte defaultValue);
    public short optShort(String property, short defaultValue);
    public int optInteger(String property, int defaultValue);
    public long optLong(String property, long defaultValue);
    public double optDouble(String property, double defaultValue);
    public float optFloat(String property, float defaultValue);
    public String  optString(String property, String defaultValue);
    public boolean optBoolean(String property, boolean defaultValue);
    public char optCharacter(String property, char defaultValue);
    public IJObject<T> optIJObject(String property, IJObject defaultValue);
    public Object opt(String property, Object defaultValue);

    public boolean has(String property);

    public Object remove(String property);

    public boolean isNull(String property);
    public boolean isIJObject(String property);
    public boolean isJsonArray(String property);

    public String toJsonString();

    public int size();

    public T toNative();

    public IJObject<T> newIJObject();
    public IJObject<T> newIJObject(T json);
    public IJObject<T> parse(String jsonString);
}
