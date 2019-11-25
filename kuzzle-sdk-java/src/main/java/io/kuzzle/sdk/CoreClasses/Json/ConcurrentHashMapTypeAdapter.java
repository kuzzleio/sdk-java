package io.kuzzle.sdk.CoreClasses.Json;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Maps.Serializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTypeAdapter extends TypeAdapter<ConcurrentHashMap<String, Object>> {


    @Override
    public void write(JsonWriter out, ConcurrentHashMap<String, Object> map) throws IOException {
        if (map == null) {
            out.nullValue();
        } else {
            out.beginObject();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                out.name(String.valueOf(entry.getKey()));
                writeObject(out, entry.getValue());
            }

            out.endObject();
        }
    }

    @Override
    public ConcurrentHashMap<String, Object> read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else if (peek == JsonToken.BEGIN_OBJECT) {
            KuzzleMap map = new KuzzleMap();
            Object key;
            Object value;

            in.beginObject();

            while(in.hasNext()) {
                key = in.nextName();
                value = readObject(in);
                if (!map.containsKey(key)) {
                    map.put((String) key, value);
                } else {
                    throw new JsonSyntaxException("duplicate key: " + key);
                }
            }

            in.endObject();

            return map;
        }
        return null;
    }

    private void writeObject(JsonWriter out, Object value) throws IOException {
        if (value instanceof Number) {
            out.value((Number)value);
        } else if (value instanceof Boolean) {
            out.value((Boolean)value);
        } else if (value instanceof String) {
            out.value((String)value);
        } else if (value instanceof ArrayList) {
            out.beginArray();
            Iterator<Object>
                    iterator = ((ArrayList<Object>)value).iterator();

            while(iterator.hasNext()) {
                writeObject(out, iterator.next());
            }

            out.endArray();
        } else if (value instanceof ConcurrentHashMap) {
            out.beginObject();
            Iterator<Map.Entry<String, Object>>
                    iterator = ((ConcurrentHashMap<String, Object>)value)
                    .entrySet()
                    .iterator();

            while(iterator.hasNext()) {
                Map.Entry<String, Object> e = iterator.next();
                out.name(e.getKey());
                writeObject(out, e.getValue());
            }

            out.endObject();
        } else if (value instanceof Serializable) {
            writeObject(out, ((Serializable)value).toMap());
        } else if (value == null) {
            out.nullValue();
        }
    }

    private Object readObject(JsonReader in) throws IOException {
        switch(in.peek()) {
            case NUMBER:
                String number = in.nextString();
                return new LazilyParsedNumber(number);
            case BOOLEAN:
                return in.nextBoolean();
            case STRING:
                return in.nextString();
            case NULL:
                in.nextNull();
                return null;
            case BEGIN_ARRAY:
                ArrayList<Object> array = new ArrayList<>();
                in.beginArray();

                while(in.hasNext()) {
                    array.add(readObject(in));
                }

                in.endArray();
                return array;
            case BEGIN_OBJECT:
                KuzzleMap map = new KuzzleMap();
                in.beginObject();

                while(in.hasNext()) {
                    String key = in.nextName();
                    Object object = readObject(in);
                    if (object != null) {
                        map.put(key, object);
                    }
                }

                in.endObject();
                return map;
            case END_DOCUMENT:
            case NAME:
            case END_OBJECT:
            case END_ARRAY:
            default:
                throw new IllegalArgumentException();
        }
    }
}
