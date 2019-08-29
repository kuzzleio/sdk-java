package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializable;
import io.kuzzle.sdk.Helpers.IJObjectHelper;

public class ErrorResponse<T> implements JsonSerializable<T> {

    public ErrorResponse(IJObject jsonObject) {
        status = jsonObject.optInteger("status", 0);
        message = jsonObject.optString("message", "");
    }

    public ErrorResponse() {

    }

    public int status;
    public String message;

    @Override
    public void fromIJObject(IJObject<T> obj) {
        status = obj.optInteger("status", 0);
        message = obj.getString(message);
    }

    @Override
    public IJObject<T> toIJObject() {
        IJObject<T> obj = IJObjectHelper.<T>newIJObject();

        obj.put("status", status);
        obj.put("message", message);
        return obj;
    }
}

