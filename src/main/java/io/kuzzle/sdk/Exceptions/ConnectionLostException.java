package io.kuzzle.sdk.Exceptions;

/**
 * Thrown to close ongoing API tasks, when the connection has been lost while
 * waiting for a result.
 */
public class ConnectionLostException extends KuzzleException {
  /**
   *
   */
  private static final long serialVersionUID = 1046624032334173580L;

  /**
   * Initializes a new instance of the ConnectionLostException.
   */
  public ConnectionLostException() {
    super(KuzzleExceptionCode.CONNECTION_LOST);
  }

}
