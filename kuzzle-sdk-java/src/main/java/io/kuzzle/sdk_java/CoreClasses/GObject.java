package io.kuzzle.sdk_java.CoreClasses;

import com.google.gson.*;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;

public class GObject implements IJObject<JsonObject> {

    private JsonObject jsonObject;

    public GObject() {
        this.jsonObject = new JsonObject();
    }

    public GObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public IJObject<JsonObject> put(String property, Byte value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Short value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Integer value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Long value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, String value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Double value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Float value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Boolean value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Character value) {
        jsonObject.addProperty(property, value);
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, IJObject<JsonObject> value) {
        if (value != null) {
            jsonObject.add(
                    property,
                    new JsonParser().parse(value.toJsonString())
            );
        } else {
            jsonObject.add(property, new JsonNull());
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Object value) {
        if (value == null) {
            jsonObject.add(property, new JsonNull());
        } else if (value instanceof JsonElement) {
            jsonObject.add(property, (JsonElement)value);
        } else if (value instanceof JsonSerializable) {
            put(property, ((JsonSerializable<JsonObject>) value).toIJObject());
        }
        return this;
    }

    @Override
    public Byte getByte(String property) {
        return jsonObject.has(property)
                ? jsonObject.get(property).getAsByte()
                : null;
    }

    @Override
    public Short getShort(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsShort()
                : null;
    }

    @Override
    public Integer getInteger(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsInt()
                : null;
    }

    @Override
    public Long getLong(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsLong()
                : null;
    }

    @Override
    public Double getDouble(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsDouble()
                : null;
    }

    @Override
    public Float getFloat(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsFloat()
                : null;
    }

    @Override
    public String getString(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsString()
                : null;
    }

    @Override
    public Boolean getBoolean(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsBoolean()
                : null;
    }

    @Override
    public Character getCharacter(String property) {
        return !isNull(property)
                ? jsonObject.get(property).getAsCharacter()
                : null;
    }

    @Override
    public IJObject<JsonObject> getIJObject(String property) {
        return !isNull(property)
                && jsonObject.get(property).isJsonObject()
                ? new GObject(jsonObject.getAsJsonObject(property))
                : null;
    }

    @Override
    public Object get(String property) {
        return !isNull(property)
                ? jsonObject.get(property)
                : null;
    }

    @Override
    public byte optByte(String property, byte defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsByte()
                : defaultValue;
    }

    @Override
    public short optShort(String property, short defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsShort()
                : defaultValue;
    }

    @Override
    public int optInteger(String property, int defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsInt()
                : defaultValue;
    }

    @Override
    public long optLong(String property, long defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsLong()
                : defaultValue;
    }

    @Override
    public double optDouble(String property, double defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsDouble()
                : defaultValue;
    }

    @Override
    public float optFloat(String property, float defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsFloat()
                : defaultValue;
    }

    @Override
    public String optString(String property, String defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsString()
                : defaultValue;
    }

    @Override
    public boolean optBoolean(String property, boolean defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsBoolean()
                : defaultValue;
    }

    @Override
    public char optCharacter(String property, char defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsCharacter()
                : defaultValue;
    }

    @Override
    public IJObject<JsonObject> optIJObject(String property, IJObject defaultValue) {
        return !isNull(property)
                && jsonObject.get(property).isJsonObject()
                ? new GObject(jsonObject.getAsJsonObject(property))
                : defaultValue;
    }

    @Override
    public Object opt(String property, Object defaultValue) {
        return !isNull(property)
                ? jsonObject.get(property).getAsByte()
                : defaultValue;
    }

    @Override
    public boolean has(String property) {
        return jsonObject.has(property);
    }

    @Override
    public Object remove(String property) {
        return null;
    }

    @Override
    public boolean isNull(String property) {
        return !jsonObject.has(property)
                || jsonObject.get(property).isJsonNull();
    }

    @Override
    public boolean isIJObject(String property) {
        return !isNull(property)
                && jsonObject.get(property).isJsonObject();
    }

    @Override
    public boolean isJsonArray(String property) {
        return !isNull(property)
                && jsonObject.get(property).isJsonArray();
    }

    @Override
    public String toJsonString() {
        return jsonObject.toString();
    }

    @Override
    public int size() {
        return jsonObject.size();
    }

    @Override
    public JsonObject toNative() {
        return jsonObject;
    }

    @Override
    public IJObject<JsonObject> newIJObject() {
        return new GObject();
    }

    @Override
    public IJObject<JsonObject> newIJObject(JsonObject json) {
        return new GObject(json);
    }

    @Override
    public IJObject<JsonObject> parse(String jsonString) {
        JsonElement json = new JsonParser().parse(jsonString);
        if (json != null
        && !json.isJsonNull()
        && json.isJsonObject()
        ) {
            return new GObject(json.getAsJsonObject());
        }
        return new GObject();
    }
}
