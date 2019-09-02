package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;
import io.kuzzle.sdk.Helpers.IJObjectHelper;

/** Represents a Kuzzle API error.
 * @param <T> The json object of the Json library you want to use.
 */
public class ErrorResponse<T> implements JsonSerializable<T> {

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
    public void fromIJObject(IJObject<T> obj) {
        status = obj.optInteger("status", 0);
        message = obj.getString("message");
        stack = obj.getString("stack");
    }

    @Override
    public IJObject<T> toIJObject() {
        IJObject<T> obj = IJObjectHelper.<T>newIJObject();

        obj.put("status", status);
        obj.put("message", message);
        obj.put("stack", stack);
        return obj;
    }
}

