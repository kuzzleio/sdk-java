package io.kuzzle.sdk.Exceptions;

/**
 * Root of all Kuzzle exceptions.
 */
public class KuzzleException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 4446507573441857492L;
  /**
   * Kuzzle API error code.
   */
  protected int status;

  /**
   * Initializes a new instance of the KuzzleException.
   * 
   * @param message
   *                  Message.
   * @param status
   *                  Status.
   */
  protected KuzzleException(String message, int status) {
    super(message);
    this.status = status;
  }

  protected KuzzleException(String message, KuzzleExceptionCode status) {
    super(message);
    this.status = status.getCode();
  }

  protected KuzzleException(KuzzleExceptionCode status) {
    super(status.getMessage());
    this.status = status.getCode();
  }

  /**
   * @return The status code of the exception.
   */
  public int getStatus() {
    return status;
  }
}
