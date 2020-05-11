package io.kuzzle.sdk.Protocol;

import io.kuzzle.sdk.Events.EventManager;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractProtocol extends EventManager {
  /**
   * Current connection state.
   * 
   * @return The state.
   */
  public abstract ProtocolState getState();

  /**
   * connect this instance.
   * 
   * @throws Exception
   */
  public abstract void connect() throws Exception;

  /**
   * Disconnect this instance.
   */
  public abstract void disconnect();

  /**
   * Send the specified payload to Kuzzle.
   * 
   * @param payload
   */
  public abstract void send(ConcurrentHashMap<String, Object> payload);
}
