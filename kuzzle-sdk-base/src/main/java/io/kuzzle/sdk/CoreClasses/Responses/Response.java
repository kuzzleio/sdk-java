package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;
import io.kuzzle.sdk.Helpers.IJObjectHelper;


/**
 * @param <T> The json object of the Json library you want to use.
 */
public class Response<T> extends JsonSerializable<T> {

    public String room;

    /**
     * Response payload (depends on the executed API action)
     */
    public IJObject<T> result;

    /**
     * Error object (null if the request finished successfully)
     */
    public ErrorResponse<T> error;

    /**
     * Request unique identifier.
     */
    public String requestId;

    /**
     * Response status, following HTTP status codes
     */
    public int status;

    /**
     * Executed Kuzzle API controller.
     */
    public String controller;

    /**
     * Executed Kuzzle API controller's action.
     */
    public String action;

    /**
     * Impacted data index.
     */
    public String index;

    /**
     * Impacted data collection.
     */
    public String collection;

    /**
     * Volatile data.
     */
    public IJObject<T> Volatile;

    // The following properties are specific to real-time notifications

    /**
     * Network protocol at the origin of the real-time notification.
     */
    public String protocol;

    /**
     * Document scope ("in" or "out")
     */
    public String scope;

    /**
     * Document state
     */
    public String state;

    /**
     * Notification timestamp (UTC)
     */
    public Long timestamp;

    /**
     * Notification type
     */
    public String type;

    @Override
    public void fromIJObject(IJObject<T> jsonObject) {
        room = jsonObject.getString("room");
        result = jsonObject.optIJObject(
                "result",
                IJObjectHelper.newIJObject()
        );
        error = null;
        if (jsonObject.isJsonObject("error")) {
            error = new ErrorResponse<T>();
            error.fromIJObject(jsonObject.<T>getJsonObject("error"));
        }
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
