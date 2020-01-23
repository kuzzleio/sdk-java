package io.kuzzle.sdk.Exceptions;

/**
 * Thrown when attempting to interact with the network while not connected.
 */
public class NotConnectedException extends KuzzleException {

  /**
   * Initializes a new instance of the NotConnectedException.
   */
  public NotConnectedException() {
    super(KuzzleExceptionCode.NOT_CONNECTED);
  }
}
