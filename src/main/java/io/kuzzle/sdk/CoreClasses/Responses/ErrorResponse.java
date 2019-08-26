package io.kuzzle.sdk.CoreClasses.Responses;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("status")
    public int status;

    @SerializedName("message")
    public String message;
}
