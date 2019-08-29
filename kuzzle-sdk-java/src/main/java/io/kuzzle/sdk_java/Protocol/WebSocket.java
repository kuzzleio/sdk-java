package io.kuzzle.sdk_java.Protocol;

import com.google.gson.JsonObject;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.AbstractWebSocket;

import java.net.URISyntaxException;

public class WebSocket extends AbstractWebSocket<JsonObject> {

    public WebSocket(String host) throws URISyntaxException, IllegalArgumentException {
        super(host);
    }

    public WebSocket(String host, WebSocketOptions options) throws URISyntaxException, IllegalArgumentException {
        super(host, options);
    }
}
