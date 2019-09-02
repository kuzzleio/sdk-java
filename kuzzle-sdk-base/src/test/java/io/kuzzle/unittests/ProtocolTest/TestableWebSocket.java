package io.kuzzle.unittests.ProtocolTest;

import io.kuzzle.runner.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.ProtocolState;

import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

public class TestableWebSocket<T> extends WebSocket<T> {
    public int stateChangedCount = 0;
    public ProtocolState lastStateDispatched = ProtocolState.CLOSE;
    public com.neovisionaries.ws.client.WebSocket
            mockedSocket = mock(com.neovisionaries.ws.client.WebSocket.class);

    public TestableWebSocket(String host)
            throws URISyntaxException, IllegalArgumentException {
        super(host);
    }

    public TestableWebSocket(String host, WebSocketOptions options)
            throws URISyntaxException, IllegalArgumentException {
        super(host, options);
        super.stateChanged.register((ProtocolState state) -> {
            stateChangedCount += 1;
            lastStateDispatched = state;
        });
    }

    public com.neovisionaries.ws.client.WebSocket getSocket() {
        return super.socket;
    }

    @Override
    protected com.neovisionaries.ws.client.WebSocket createClientSocket() {
        return mockedSocket;
    }
}
