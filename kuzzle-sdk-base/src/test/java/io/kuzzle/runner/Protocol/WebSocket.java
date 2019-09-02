package io.kuzzle.runner.Protocol;

import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.AbstractWebSocket;

import java.net.URISyntaxException;

public class WebSocket<T> extends AbstractWebSocket<T> {

    public WebSocket(String host)
            throws URISyntaxException, IllegalArgumentException {
        super(host);
    }

    public WebSocket(String host, WebSocketOptions options)
            throws URISyntaxException, IllegalArgumentException {
        super(host, options);
    }
}
