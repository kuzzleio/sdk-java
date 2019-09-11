package io.kuzzle.runner.CoreClasses;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;

public class GObject implements IJObject<JsonObject> {

    private Object object;
    private Type type;

    public GObject() {
        this.object = new JsonObject();
        type = Type.JSON;
    }

    public GObject(JsonObject object) {
        this.object = object;
        type = Type.JSON;
    }

    public GObject(Character object) {
        this.object = object;
        type = Type.CHARACTER;
    }

    public GObject(Boolean object) {
        this.object = object;
        type = Type.BOOLEAN;
    }

    public GObject(Byte object) {
        this.object = object;
        type = Type.BYTE;
    }

    public GObject(Short object) {
        this.object = object;
        type = Type.SHORT;
    }

    public GObject(Integer object) {
        this.object = object;
        type = Type.INTEGER;
    }

    public GObject(Long object) {
        this.object = object;
        type = Type.LONG;
    }

    public GObject(Float object) {
        this.object = object;
        type = Type.FLOAT;
    }

    public GObject(Double object) {
        this.object = object;
        type = Type.DOUBLE;
    }

    public GObject(String object) {
        this.object = object;
        type = Type.STRING;
    }

    @Override
    public IJObject<JsonObject> put(String property, Byte value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Short value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Integer value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Long value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, String value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Double value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Float value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Boolean value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Character value) {
        if (isJsonObject()) {
            ((JsonObject)object).addProperty(property, value);
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, IJObject<JsonObject> value) {
        if (isJsonObject()) {
            if (value != null) {
                JsonObject json = ((JsonObject) object);

                switch (value.getType()) {
                    case BYTE:
                        json.addProperty(property, value.toByte());
                        break;
                    case SHORT:
                        json.addProperty(property, value.toShort());
                        break;
                    case INTEGER:
                        json.addProperty(property, value.toInteger());
                        break;
                    case LONG:
                        json.addProperty(property, value.toLong());
                        break;
                    case FLOAT:
                        json.addProperty(property, value.toFloat());
                        break;
                    case DOUBLE:
                        json.addProperty(property, value.toDouble());
                        break;
                    case STRING:
                        json.addProperty(property, value.toString());
                        break;
                    case BOOLEAN:
                        json.addProperty(property, value.toBoolean());
                        break;
                    case CHARACTER:
                        json.addProperty(property, value.toCharacter());
                        break;
                    case JSON:
                        json.add(property,
                                new JsonParser().parse(value.toJsonString())
                        );
                        break;
                    default: break;
                }

            } else {
                ((JsonObject) object).add(property, new JsonNull());
            }
        }
        return this;
    }

    @Override
    public IJObject<JsonObject> put(String property, Object value) {
        if (isJsonObject()) {
            if (value == null) {
                ((JsonObject) object).add(property, new JsonNull());
            } else if (value instanceof JsonElement) {
                ((JsonObject) object).add(property, (JsonElement) value);
            } else if (value instanceof JsonSerializable) {
                put(property, ((JsonSerializable<JsonObject>) value).toIJObject());
            }
        }
        return this;
    }

    @Override
    public Byte getByte(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsByte()
                : null;
    }

    @Override
    public Short getShort(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsShort()
                : null;
    }

    @Override
    public Integer getInteger(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsInt()
                : null;
    }

    @Override
    public Long getLong(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsLong()
                : null;
    }

    @Override
    public Double getDouble(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsDouble()
                : null;
    }

    @Override
    public Float getFloat(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsFloat()
                : null;
    }

    @Override
    public String getString(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsString()
                : null;
    }

    @Override
    public Boolean getBoolean(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsBoolean()
                : null;
    }

    @Override
    public Character getCharacter(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsCharacter()
                : null;
    }

    @Override
    public IJObject<JsonObject> getAsIJObject(String property) {
            if (isNull(property)) return null;

            if (getBoolean(property) != null) {
                return new GObject(getBoolean(property));
            } else if (getCharacter(property) != null) {
                return new GObject(getCharacter(property));
            } else if (getByte(property) != null) {
                return new GObject(getByte(property));
            } else if (getShort(property) != null) {
                return new GObject(getShort(property));
            } else if (getInteger(property) != null) {
                return new GObject(getInteger(property));
            } else if (getLong(property) != null) {
                return new GObject(getLong(property));
            } else if (getFloat(property) != null) {
                return new GObject(getFloat(property));
            } else if (getDouble(property) != null) {
                return new GObject(getDouble(property));
            } else if (getString(property) != null) {
                return new GObject(getString(property));
            } else if (isJsonObject(property)) {
                return new GObject(((JsonObject) object).getAsJsonObject(property));
            }
            return null;
    }

    @Override
    public IJObject<JsonObject> getJsonObject(String property) {
        if (!isNull(property)
            && ((JsonObject) object).get(property).isJsonObject()
        ) {
            return new GObject(
                    ((JsonObject) object).getAsJsonObject(property)
            );
        }
        return null;
    }

    @Override
    public Object get(String property) {
        return !isNull(property)
                ? ((JsonObject) object).get(property)
                : null;
    }

    @Override
    public String toString() {
        if (!isString()) return null;
        return (String)object;
    }

    @Override
    public Byte toByte() {
        if (!isByte()) return null;
        return (Byte)object;
    }

    @Override
    public Short toShort() {
        if (!isShort()) return null;
        return (Short) object;
    }

    @Override
    public Integer toInteger() {
        if (!isInteger()) return null;
        return (Integer) object;
    }

    @Override
    public Long toLong() {
        if (!isLong()) return null;
        return (Long) object;
    }

    @Override
    public Double toDouble() {
        if (!isDouble()) return null;
        return (Double) object;
    }

    @Override
    public Float toFloat() {
        if (!isFloat()) return null;
        return (Float) object;
    }

    @Override
    public Boolean toBoolean() {
        if (!isBoolean()) return null;
        return (Boolean) object;
    }

    @Override
    public Character toCharacter() {
        if (!isCharacter()) return null;
        return (Character) object;
    }

    @Override
    public boolean isByte() {
        return type == Type.BYTE;
    }

    @Override
    public boolean isShort() {
        return type == Type.SHORT;
    }

    @Override
    public boolean isInteger() {
        return type == Type.INTEGER;
    }

    @Override
    public boolean isLong() {
        return type == Type.LONG;
    }

    @Override
    public boolean isDouble() {
        return type == Type.DOUBLE;
    }

    @Override
    public boolean isFloat() {
        return type == Type.FLOAT;
    }

    @Override
    public boolean isString() {
        return type == Type.STRING;
    }

    @Override
    public boolean isBoolean() {
        return type == Type.BOOLEAN;
    }

    @Override
    public boolean isCharacter() {
        return type == Type.CHARACTER;
    }

    @Override
    public boolean isJsonObject() {
        return type == Type.JSON;
    }

    @Override
    public byte optByte(String property, byte defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsByte()
                : defaultValue;
    }

    @Override
    public short optShort(String property, short defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsShort()
                : defaultValue;
    }

    @Override
    public int optInteger(String property, int defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsInt()
                : defaultValue;
    }

    @Override
    public long optLong(String property, long defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsLong()
                : defaultValue;
    }

    @Override
    public double optDouble(String property, double defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsDouble()
                : defaultValue;
    }

    @Override
    public float optFloat(String property, float defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsFloat()
                : defaultValue;
    }

    @Override
    public String optString(String property, String defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsString()
                : defaultValue;
    }

    @Override
    public boolean optBoolean(String property, boolean defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsBoolean()
                : defaultValue;
    }

    @Override
    public char optCharacter(String property, char defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsCharacter()
                : defaultValue;
    }

    @Override
    public IJObject<JsonObject> optIJObject(String property, IJObject defaultValue) {
        IJObject<JsonObject> obj;
        if ( (obj = getAsIJObject(property)) != null)
            return obj;
        return defaultValue;
    }

    @Override
    public Object opt(String property, Object defaultValue) {
        return !isNull(property)
                ? ((JsonObject) object).get(property).getAsByte()
                : defaultValue;
    }

    @Override
    public boolean has(String property) {
        if (!isJsonObject()) return false;

        return ((JsonObject) object).has(property);
    }

    @Override
    public Object remove(String property) {
        if (!isJsonObject()) return null;

        return ((JsonObject) object).remove(property);
    }

    @Override
    public boolean isNull(String property) {
        if (!isJsonObject()) return true;

        return !((JsonObject) object).has(property)
                || ((JsonObject) object).get(property).isJsonNull();
    }

    @Override
    public boolean isJsonObject(String property) {
        return !isNull(property)
                && ((JsonObject) object).get(property).isJsonObject();
    }

    @Override
    public String toJsonString() {
        if (!isJsonObject()) return null;

        return object.toString();
    }

    @Override
    public int size() {
        if (!isJsonObject()) return 0;

        return ((JsonObject) object).size();
    }

    @Override
    public JsonObject toNative() {
        if (!isJsonObject()) return null;

        return (JsonObject)object;
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
    public IJObject<JsonObject> newIJObject(Byte obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Short obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Integer obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Long obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Float obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Double obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Boolean obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(Character obj) {
        return new GObject(obj);
    }

    @Override
    public IJObject<JsonObject> newIJObject(String obj) {
        return new GObject(obj);
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

    @Override
    public Type getType() {
        return type;
    }
}
