import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;

WebSocketOptions options = new WebSocketOptions();
options.setAutoReconnect(true)
        .setConnectionTimeout(42000)
        .setPort(7513)
        .setSsl(false);

WebSocket ws = new WebSocket("localhost", options);
