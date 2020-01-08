package io.kuzzle.test.ProtocolTest;

import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

public class TestableWebSocket extends WebSocket {
  public int stateChangedCount = 0;
  public ProtocolState lastStateDispatched = ProtocolState.CLOSE;
  public com.neovisionaries.ws.client.WebSocket mockedSocket = mock(com.neovisionaries.ws.client.WebSocket.class);

  public TestableWebSocket(String host) throws URISyntaxException, IllegalArgumentException {
    super(host);
  }

  public TestableWebSocket(String host, WebSocketOptions options) throws URISyntaxException, IllegalArgumentException {
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
