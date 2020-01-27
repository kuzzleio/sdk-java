package io.kuzzle.sdk.Exceptions;

import io.kuzzle.sdk.CoreClasses.Responses.Response;

/**
 * Passed to async tasks when an API request returns an error.
 */
public class ApiErrorException extends KuzzleException {
  /**
   *
   */
  private static final long serialVersionUID = 666379398727075901L;

  /**
   * Kuzzle API stack trace
   */
  protected String stack;

  /**
   * Kuzzle API error unique identifier
   */
  protected String id;

  /**
   * Initializes a new instance of the ApiErrorException
   * 
   * @param response Kuzzle API Response.
   */
  public ApiErrorException(Response response) {
    super(response.error != null ? response.error.message : null, response.status);
    if (response.error != null) {
      this.stack = response.error.stack;
      this.id = response.error.id;
    }
  }

  public String getStack() {
    return this.stack;
  }

  public String getId() {
    return this.id;
  }
}
