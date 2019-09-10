package io.kuzzle.sdk.CoreClasses.Json;

/**
 * @param <T> The json object of the Json library you want to use.
 */
public interface IJObject<T> {

    /**
     * Associate an object to the specified property key.
     */
    public IJObject<T> put(String property, Byte value);
    public IJObject<T> put(String property, Short value);
    public IJObject<T> put(String property, Integer value);
    public IJObject<T> put(String property, Long value);
    public IJObject<T> put(String property, String value);
    public IJObject<T> put(String property, Double value);
    public IJObject<T> put(String property, Float value);
    public IJObject<T> put(String property, Boolean value);
    public IJObject<T> put(String property, Character value);
    public IJObject<T> put(String property, IJObject<T> value);
    public IJObject<T> put(String property, Object value);

    /**
     * Return the object at the specified property key.
     * Return null if the property does not exists.
     */
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

    /**
     * Return the object at the specified property key.
     * If null, return the specified default value.
     */
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


    /** Check if the property exist.
     * @param property Property name.
     * @return If the property exist.
     */
    public boolean has(String property);

    /** Remove a property.
     * @param property Property name.
     * @return The object at the specified property.
     */
    public Object remove(String property);

    /** Check if a property is null.
     * @param property Property name.
     * @return If the property is null.
     */
    public boolean isNull(String property);

    /** Check if the property is an IJObject.
     * @param property Property name.
     * @return If the property is an IJObject.
     */
    public boolean isIJObject(String property);

    /** Check if the property is a JsonArray.
     * @param property Property name.
     * @return If the property is an JsonArray.
     */
    public boolean isJsonArray(String property);


    /** Convert an IJObject to String.
     * @return A Json String that represent the IJObject.
     */
    public String toJsonString();

    /** Get the number of elements in the IJObject.
     * @return The number of elements in the IJObject.
     */
    public int size();

    /** Convert the IJObject to a Json Object of the specified json library.
     * @return A Json Object of the specified json library.
     */
    public T toNative();


    /** Create a new instance of IJObject
     * @return A new instance of IJObject.
     */
    public IJObject<T> newIJObject();

    /** Create a new instance of IJObject from a Json Object of the specified json library
     * @param json A Json Object of the specified json library.
     * @return A new instance of IJObject.
     */
    public IJObject<T> newIJObject(T json);

    /** Parse a Json String and create a new instance of IJObject.
     * @param jsonString A Json String.
     * @return A new instance of IJObject created from the Json String.
     */
    public IJObject<T> parse(String jsonString);
}
