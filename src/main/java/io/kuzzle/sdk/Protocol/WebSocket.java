package io.kuzzle.sdk.Protocol;

import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.sun.istack.internal.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

public class WebSocket extends AbstractProtocol {

    private CompletableFuture connecting;
    private CountDownLatch waiter;
    private BlockingDeque<JsonObject> queue;
    private com.neovisionaries.ws.client.WebSocket socket;
    private ProtocolState state = ProtocolState.CLOSE;
    private URI uri;

    public ProtocolState getState() {
        return state;
    }

    public WebSocket(@NotNull String host, int port) throws URISyntaxException, IllegalArgumentException {
        this(host, port, false);
    }

    public WebSocket(@NotNull String host, int port, boolean ssl) throws URISyntaxException, IllegalArgumentException {
        super();
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Host name/address can't be empty");
        }

        this.uri = new URI((ssl ? "wss" : "ws") + "://" + host + ":" + port + "/");
        this.queue = new LinkedBlockingDeque<>();
    }

    @Override
    public void send(JsonObject payload) {
        queue.add(payload);
    }

    @Override
    public void connect() throws Exception {
        if (socket != null) {
            return;
        }

        socket = new WebSocketFactory().createSocket(uri);

        socket.connect();
        state = ProtocolState.OPEN;
        dispatchStateChange(state);
        Dequeue();

        socket.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, String text) throws Exception {
                super.onTextMessage(websocket, text);
                dispatchMessageEvent(text);
            }
        });
    }

    @Override
    public void disconnect() {
        CloseState();
    }

    private void CloseState() {
        socket.disconnect();
        state = ProtocolState.CLOSE;
        socket = null;
        dispatchStateChange(state);
    }

    private Thread Dequeue() {
        Thread thread = new Thread(() -> {
            while (state == ProtocolState.OPEN) {
                JsonObject payload = queue.poll();
                if (payload != null) {
                    socket.sendText(payload.toString());
                }
            }
        });
        thread.start();
        return thread;
    }
}
