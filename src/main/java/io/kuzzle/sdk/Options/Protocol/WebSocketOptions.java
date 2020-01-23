package io.kuzzle.sdk.Options.Protocol;

import io.kuzzle.sdk.Options.KuzzleOptions;

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
  public WebSocketOptions withPort(int port) {
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
  public WebSocketOptions withSsl(boolean ssl) {
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
  public WebSocketOptions withConnectionTimeout(int connectionTimeout) {
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
  public WebSocketOptions withAutoReconnect(boolean autoReconnect) {
    this.autoReconnect = autoReconnect;
    return this;
  }
}
