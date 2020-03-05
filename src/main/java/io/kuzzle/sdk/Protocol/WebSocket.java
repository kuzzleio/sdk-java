package io.kuzzle.sdk.Protocol;

import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializer;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class WebSocket extends AbstractProtocol {

  protected final boolean ssl;
  protected final int port;
  protected BlockingDeque<ConcurrentHashMap<String, Object>> queue;
  protected com.neovisionaries.ws.client.WebSocket socket;
  protected ProtocolState state = ProtocolState.CLOSE;
  protected URI uri;
  protected int connectionTimeout;
  protected boolean autoReconnect;
  protected long reconnectionDelay;
  protected long reconnectionRetries;

  public WebSocket(URI uri) throws Exception {
    WebSocketOptions options = new WebSocketOptions();
    if (uri.getPort() > -1) {
      options.setPort(uri.getPort());
    }
    if (uri.getHost() == null || uri.getHost().isEmpty()) {
      throw new URISyntaxException("Missing host", "Could not find host part");
    }
    if (uri.getScheme() != null) {
      options.setSsl(uri.getScheme().equals("wss"));
    }
    WebSocketOptions wsOptions = options != null ? new WebSocketOptions(options)
        : new WebSocketOptions();

    ssl = wsOptions.isSsl();
    port = wsOptions.getPort();
    connectionTimeout = wsOptions.getConnectionTimeout();
    autoReconnect = wsOptions.isAutoReconnect();
    reconnectionDelay = wsOptions.getReconnectionDelay();
    reconnectionRetries = wsOptions.getReconnectionRetries();

    this.uri = new URI(
        (ssl ? "wss" : "ws") + "://" + uri.getHost() + ":" + port + "/");
    this.queue = new LinkedBlockingDeque<>();
  }

  public WebSocket(URI uri, WebSocketOptions options)
      throws URISyntaxException, IllegalArgumentException {
    this(uri.getHost(), options);
  }

  public WebSocket(String host)
      throws IllegalArgumentException, URISyntaxException {
    this(host, new WebSocketOptions());
  }

  /**
   * @param host    Kuzzle host address
   * @param options WebSocket options
   * @throws URISyntaxException
   * @throws IllegalArgumentException
   */
  public WebSocket(String host, WebSocketOptions options)
      throws URISyntaxException, IllegalArgumentException {
    super();

    WebSocketOptions wsOptions = options != null ? new WebSocketOptions(options)
        : new WebSocketOptions();

    ssl = wsOptions.isSsl();
    port = wsOptions.getPort();
    connectionTimeout = wsOptions.getConnectionTimeout();
    autoReconnect = wsOptions.isAutoReconnect();
    reconnectionDelay = wsOptions.getReconnectionDelay();
    reconnectionRetries = wsOptions.getReconnectionRetries();

    if (host == null || host.isEmpty()) {
      throw new IllegalArgumentException("Host name/address can't be empty");
    }
    this.uri = new URI((ssl ? "wss" : "ws") + "://" + host + ":" + port + "/");
    this.queue = new LinkedBlockingDeque<>();
  }

  public ProtocolState getState() {
    return state;
  }

  @Override
  public void send(ConcurrentHashMap<String, Object> payload) {
    queue.add(payload);
  }

  protected com.neovisionaries.ws.client.WebSocket createClientSocket()
      throws IOException {
    WebSocketFactory wsFactory = new WebSocketFactory();

    if (connectionTimeout > -1) {
      wsFactory = wsFactory.setConnectionTimeout(connectionTimeout);
    }

    if (ssl) {

      wsFactory.setSocketFactory(SSLSocketFactory.getDefault());
    }

    return wsFactory.createSocket(uri);
  }

  /**
   * Connects to a Kuzzle server.
   *
   * @throws IOException
   * @throws WebSocketException
   * @throws Exception
   */
  @Override
  public void connect() throws IOException, WebSocketException {
    if (socket != null) {
      return;
    }

    socket = createClientSocket();

    socket.connect();
    state = ProtocolState.OPEN;
    super.trigger(Event.networkStateChange, state);
    Dequeue();

    socket.addListener(new WebSocketAdapter() {
      @Override
      public void onTextMessage(
          com.neovisionaries.ws.client.WebSocket websocket, String text)
          throws Exception {
        super.onTextMessage(websocket, text);
        WebSocket.super.trigger(Event.networkResponseReceived, text);
      }

      @Override
      public void onDisconnected(com.neovisionaries.ws.client.WebSocket websocket,
                                 WebSocketFrame serverCloseFrame,
                                 WebSocketFrame clientCloseFrame,
                                 boolean closedByServer) {
        state = ProtocolState.CLOSE;
        WebSocket.super.trigger(Event.networkStateChange, state);
        CloseState(autoReconnect);
      }
    });
  }

  private void reconnect() {
    if (state == ProtocolState.RECONNECTING) {
      return;
    }

    socket.clearListeners();
    state = ProtocolState.RECONNECTING;
    super.trigger(Event.networkStateChange, state);
    for (int i = 0; i < reconnectionRetries; i++) {
      try {
        connect();
      } catch (WebSocketException e) {
        try {
          Thread.sleep(reconnectionDelay);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      } catch (IOException e) {
        try {
          Thread.sleep(reconnectionDelay);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  /**
   * Disconnects this instance.
   */
  @Override
  public void disconnect() {
    CloseState(false);
  }

  synchronized protected void CloseState(final boolean tryToReconnect) {
    if (socket != null && state != ProtocolState.CLOSE) {
      if (tryToReconnect) {
        new Thread(
            () -> reconnect()
        ).start();
      } else {
        socket.disconnect();
        state = ProtocolState.CLOSE;
        socket = null;
        super.trigger(Event.networkStateChange, state);
      }
    }
  }

  protected Thread Dequeue() {
    Thread thread = new Thread(() -> {
      while (state == ProtocolState.OPEN) {
        ConcurrentHashMap<String, Object> payload = queue.poll();
        if (payload != null) {
          String rawJson = JsonSerializer.serialize(payload);
          socket.sendText(rawJson);
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
    this.connectionTimeout = connectionTimeout < 0 ? -1 : connectionTimeout;
  }
}
