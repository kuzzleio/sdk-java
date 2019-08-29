package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;
import io.kuzzle.sdk.Helpers.IJObjectHelper;

public class Response<T> implements JsonSerializable<T> {

    public String room;
    public IJObject result;
    public ErrorResponse error;
    public String requestId;
    public int status;
    public String controller;
    public String action;
    public String index;
    public String collection;
    public IJObject Volatile;
    public String protocol;
    public String scope;
    public String state;
    public Long timestamp;
    public String type;

    @Override
    public void fromIJObject(IJObject<T> jsonObject) {
        room = jsonObject.getString("room");
        result = jsonObject.optIJObject(
                "result",
                IJObjectHelper.newIJObject()
        );
        error = jsonObject.isIJObject("error")
                ? new ErrorResponse(jsonObject.getIJObject("error"))
                : null;
        requestId = jsonObject.getString("requestId");
        status = jsonObject.optInteger("status", 0);
        controller = jsonObject.getString("controller");
        action = jsonObject.getString("action");
        index = jsonObject.getString("index");
        collection = jsonObject.getString("collection");
        Volatile = jsonObject.optIJObject(
                "volatile",
                IJObjectHelper.newIJObject()
        );
        protocol = jsonObject.getString("protocol");
        scope = jsonObject.getString("scope");
        state = jsonObject.getString("state");
        timestamp = jsonObject.getLong("timestamp");
        type = jsonObject.getString("type");
    }

    @Override
    public IJObject<T> toIJObject() {
        IJObject<T> obj = IJObjectHelper.<T>newIJObject();

        obj.put("room", room);
        obj.put("result", result);
        obj.put("error", error);
        obj.put("requestId", requestId);
        obj.put("status", status);
        obj.put("controller", controller);
        obj.put("action", action);
        obj.put("index", index);
        obj.put("collection", collection);
        obj.put("volatile", Volatile);
        obj.put("protocol", protocol);
        obj.put("scope", scope);
        obj.put("status", status);
        obj.put("timestamp", timestamp);
        obj.put("type", type);

        return obj;
    }
}
