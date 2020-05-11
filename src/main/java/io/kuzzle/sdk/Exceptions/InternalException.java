package io.kuzzle.sdk.Exceptions;

/**
 * Passed to async tasks when an API request returns an error.
 */
public class InternalException extends KuzzleException {

  /**
   *
   */
  private static final long serialVersionUID = -7004783427215842992L;

  /**
   * Initializes a new instance of the InternalException
   */
  public InternalException(String message, KuzzleExceptionCode status) {
    super(message, status);
  }

  public InternalException(KuzzleExceptionCode status) {
    super(status);
  }

}
