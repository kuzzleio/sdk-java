package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Maps.CustomMap;
import io.kuzzle.sdk.CoreClasses.Maps.Serializable;

import java.util.concurrent.ConcurrentHashMap;

public class ErrorResponse implements Serializable {

    public ErrorResponse() {

    }

    /**
     * Response status, following HTTP status codes.
     */
    public int status;

    /**
     * Error message
     */
    public String message;

    /**
     * Error stack
     */
    public String stack;

    @Override
    public void fromMap(ConcurrentHashMap<String, Object> map) {
        if (map == null) return;

        CustomMap customMap = CustomMap.getCustomMap(map);

        status = customMap.optNumber("status", 0).intValue();
        message = customMap.getString("message");
        stack = customMap.getString("stack");
    }

    @Override
    public ConcurrentHashMap<String, Object> toMap() {
        ConcurrentHashMap<String, Object> map = new CustomMap();

        map.put("status", status);
        map.put("message", message);
        map.put("stack", stack);
        return map;
    }
}

