package io.kuzzle.sdk.Protocol;

import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

public abstract class AbstractWebSocket<T> extends AbstractProtocol<T> {

    protected BlockingDeque<IJObject> queue;
    protected com.neovisionaries.ws.client.WebSocket socket;
    protected ProtocolState state = ProtocolState.CLOSE;
    protected URI uri;

    protected final boolean ssl;
    protected final int port;
    protected int connectionTimeout;

    public ProtocolState getState() {
        return state;
    }

    public AbstractWebSocket(String host)
            throws URISyntaxException, IllegalArgumentException {
        this(host, new WebSocketOptions());
    }

    public AbstractWebSocket(
            String host,
            WebSocketOptions options
    ) throws URISyntaxException, IllegalArgumentException {
        super();

        WebSocketOptions wsOptions =
                options != null
                ? new WebSocketOptions(options)
                : new WebSocketOptions();

        ssl = wsOptions.isSsl();
        port = wsOptions.getPort();
        connectionTimeout = wsOptions.getConnectionTimeout();

        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Host name/address can't be empty");
        }
        this.uri = new URI((ssl ? "wss" : "ws") + "://" + host + ":" + port + "/");
        this.queue = new LinkedBlockingDeque<>();
    }

    @Override
    public void send(IJObject payload) {
        queue.add(payload);
    }

    protected com.neovisionaries.ws.client.WebSocket createClientSocket() throws IOException {
        WebSocketFactory wsFactory = new WebSocketFactory();

        if (connectionTimeout > -1) {
            wsFactory = wsFactory.setConnectionTimeout(connectionTimeout);
        }

        if (ssl) {

            wsFactory.setSocketFactory(SSLSocketFactory.getDefault());
        }

        return wsFactory.createSocket(uri);
    }

    @Override
    public void connect() throws Exception {
        if (socket != null) {
            return;
        }

        socket = createClientSocket();

        socket.connect();
        state = ProtocolState.OPEN;
        dispatchStateChange(state);
        Dequeue();

        socket.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(
                    com.neovisionaries.ws.client.WebSocket websocket,
                    String text
            ) throws Exception {
                super.onTextMessage(websocket, text);
                dispatchMessageEvent(text);
            }
        });
    }

    @Override
    public void disconnect() {
        CloseState();
    }

    protected void CloseState() {
        if (socket != null) {
            socket.disconnect();
            state = ProtocolState.CLOSE;
            socket = null;
            dispatchStateChange(state);
        }
    }

    protected Thread Dequeue() {
        Thread thread = new Thread(() -> {
            while (state == ProtocolState.OPEN) {
                IJObject payload = queue.poll();
                if (payload != null) {
                    socket.sendText(payload.toJsonString());
                }
            }
        });
        thread.start();
        return thread;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout =
                connectionTimeout < 0
                ? -1
                : connectionTimeout;
    }
}
