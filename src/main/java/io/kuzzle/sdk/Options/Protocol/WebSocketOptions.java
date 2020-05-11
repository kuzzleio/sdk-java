package io.kuzzle.sdk.Options.Protocol;

public class WebSocketOptions {

  /**
   * The port to use to connect.
   */
  private int port = 7512;

  /**
   * If we use SSL connection.
   */
  private boolean ssl = false;

  /**
   * The duration before the connection timeout.
   */
  private int connectionTimeout = -1;

  /**
   * If the websocket auto reconnects.
   */
  private boolean autoReconnect = true;

  /**
   * The number of milliseconds between 2 automatic reconnection attempts.
   */
  private long reconnectionDelay = 1000;

  /**
   * The maximum number of automatic reconnection attempts.
   */
  private long reconnectionRetries = 20;

  /**
   * Initialize a new WebSocketOptions instance.
   */
  public WebSocketOptions() {
  }

  /**
   * Initialize a new WebSocketOptions instance and copy other WebSocketOptions
   * fields
   * 
   * @param other
   */
  public WebSocketOptions(WebSocketOptions other) {
    this.port = other.port;
    this.ssl = other.ssl;
    this.connectionTimeout = other.connectionTimeout;
    this.autoReconnect = other.autoReconnect;
    this.reconnectionDelay = other.reconnectionDelay;
    this.reconnectionRetries = other.reconnectionRetries;
  }

  /**
   * @return The port used to connect.
   */
  public int getPort() {
    return port;
  }

  /**
   * Set the port to use to connect.
   * 
   * @param port
   * @return This WebSocketOptions instance.
   */
  public WebSocketOptions setPort(int port) {
    this.port = port >= 0 ? port : 7512;
    return this;
  }

  /**
   * @return If we use SSL connection.
   */
  public boolean isSsl() {
    return ssl;
  }

  /**
   * Set if we use SSL connection.
   * 
   * @param ssl
   * @return This WebSocketOptions instance.
   */
  public WebSocketOptions setSsl(boolean ssl) {
    this.ssl = ssl;
    return this;
  }

  /**
   * @return The duration before the connection timeout.
   */
  public int getConnectionTimeout() {
    return connectionTimeout;
  }

  /**
   * Set the duration before the connection timeout.
   * 
   * @param connectionTimeout
   * @return This WebSocketOptions instance.
   */
  public WebSocketOptions setConnectionTimeout(int connectionTimeout) {
    this.connectionTimeout = connectionTimeout < 0 ? -1 : connectionTimeout;
    return this;
  }

  /**
   * @return If the websocket auto reconnects.
   */
  public boolean isAutoReconnect() {
    return autoReconnect;
  }

  /**
   * Set if the websocket auto reconnects.
   * 
   * @param autoReconnect
   * @return This WebSocketOptions instance.
   */
  public WebSocketOptions setAutoReconnect(boolean autoReconnect) {
    this.autoReconnect = autoReconnect;
    return this;
  }

  public long getReconnectionDelay() {
    return reconnectionDelay;
  }

  public WebSocketOptions setReconnectionDelay(long reconnectionDelay) {
    this.reconnectionDelay = reconnectionDelay;
    return this;
  }

  public long getReconnectionRetries() {
    return reconnectionRetries;
  }

  public WebSocketOptions setReconnectionRetries(long reconnectionRetries) {
    this.reconnectionRetries = reconnectionRetries;
    return this;
  }
}
