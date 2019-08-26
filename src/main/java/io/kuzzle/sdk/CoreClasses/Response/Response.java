package io.kuzzle.sdk.CoreClasses.Response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("room")
    public String room;

    @SerializedName("result")
    public JsonObject result;

    @SerializedName("error")
    public ErrorResponse error;

    @SerializedName("requestId")
    public String requestId;

    @SerializedName("status")
    public int status;

    @SerializedName("controller")
    public String controller;

    @SerializedName("action")
    public String action;

    @SerializedName("index")
    public String index;

    @SerializedName("collection")
    public String collection;

    @SerializedName("volatile")
    public JsonObject Volatile;

    @SerializedName("protocol")
    public String protocol;

    @SerializedName("scope")
    public String scope;

    @SerializedName("state")
    public String state;

    @SerializedName("timestamp")
    public Long timesteamp;

    @SerializedName("type")
    public String type;

}
