package io.kuzzle.sdk.Protocol;

import com.sun.istack.internal.NotNull;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Kuzzle;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

public class WebSocket extends AbstractProtocol {

    private CountDownLatch waiter;
    private BlockingDeque<JSONObject> queue;
    private WebSocketClient socket;
    private ProtocolState state = ProtocolState.CLOSE;

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

        URI uri = new URI((ssl ? "wss" : "ws") + "://" + host + ":" + port + "/");
        this.queue = new LinkedBlockingDeque<>();
        this.socket = new WebSocketClient(uri);
        this.socket.disableAutomaticReconnection();
        this.waiter = new CountDownLatch(1);
    }

    @Override
    public void send(JSONObject payload) {
        queue.add(payload);
    }

    @Override
    public void connect() throws Exception {
        socket.connect();
        this.waiter.await();
    }

    @Override
    public void disconnect() {
        state = ProtocolState.CLOSE;
        socket.close();
    }

    private void Dequeue() {
        Thread thread = new Thread(() -> {
            while (state == ProtocolState.OPEN) {
                JSONObject payload = queue.poll();
                if (payload != null) {
                    socket.send(payload.toString());
                }
            }
        });
        thread.start();
    }

    private class WebSocketClient extends tech.gusavila92.websocketclient.WebSocketClient {

        public WebSocketClient(URI uri) {
            super(uri);
        }

        @Override
        public void onOpen() {
            state = ProtocolState.OPEN;
            waiter.countDown();
            Dequeue();
        }

        @Override
        public void onTextReceived(String message) {
            dispatchMessageEvent(message);
        }

        @Override
        public void onBinaryReceived(byte[] data) {

        }

        @Override
        public void onPingReceived(byte[] data) {

        }

        @Override
        public void onPongReceived(byte[] data) {

        }

        @Override
        public void onException(Exception e) {
            state = ProtocolState.CLOSE;
        }

        @Override
        public void onCloseReceived() {
            state = ProtocolState.CLOSE;
        }
    }
}
