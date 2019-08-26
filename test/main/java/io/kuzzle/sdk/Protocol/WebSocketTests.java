package main.java.io.kuzzle.sdk.Protocol;

import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import main.java.io.kuzzle.sdk.TestableKuzzle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class WebSocketTests {

    private TestableWebSocket socket;
    private String host;
    private WebSocketOptions options;

    @Before
    public void setup() throws URISyntaxException {
        host = "foo";
        options = new WebSocketOptions()
                      .withPort(1234)
                      .withSsl(true);
        socket = new TestableWebSocket(host, options);
    }

    @Test
    public void constructorNotConnected() throws URISyntaxException {
        TestableWebSocket ws = new TestableWebSocket(host, options);

        Assert.assertEquals(ProtocolState.CLOSE, ws.getState());
        Assert.assertNull(ws.getSocket());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullHost() throws URISyntaxException {
        TestableWebSocket ws = new TestableWebSocket(null, options);
    }

    @Test
    public void connectTest() throws Exception {
        when(socket.mockedSocket.connect()).thenAnswer(invocation -> null);

        socket.connect();

        Assert.assertNotNull(socket.getSocket());

        socket.connect();
        socket.connect();
        socket.connect();

        verify(socket.mockedSocket, times(1)).connect();

        Assert.assertEquals(ProtocolState.OPEN, socket.getState());
        Assert.assertEquals(1, socket.stateChangedCount);
        Assert.assertEquals(ProtocolState.OPEN, socket.lastStateDispatched);
    }

    @Test
    public void disconnectTest() throws Exception {
        when(socket.mockedSocket.connect()).thenAnswer(invocation -> null);

        socket.connect();

        Assert.assertEquals(ProtocolState.OPEN, socket.getState());
        Assert.assertEquals(1, socket.stateChangedCount);
        Assert.assertEquals(ProtocolState.OPEN, socket.lastStateDispatched);

        socket.disconnect();
        socket.disconnect();
        socket.disconnect();

        verify(socket.mockedSocket, times(1)).disconnect();
    }

    @Test
    public void disconnectWithoutEverConnecting() throws Exception {
        Assert.assertEquals(ProtocolState.CLOSE, socket.getState());
        Assert.assertEquals(0, socket.stateChangedCount);

        socket.disconnect();

        Assert.assertEquals(ProtocolState.CLOSE, socket.getState());
        Assert.assertEquals(0, socket.stateChangedCount);
    }
}
