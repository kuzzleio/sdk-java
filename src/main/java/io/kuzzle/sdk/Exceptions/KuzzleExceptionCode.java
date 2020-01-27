package io.kuzzle.sdk.Exceptions;

public enum KuzzleExceptionCode {
  MISSING_REQUESTID(0, "Missing field requestId"),
  MSSING_QUERY(400, "You must provide a query"),
  NOT_CONNECTED(500, "Not connected."),
  CONNECTION_LOST(500, "Connection lost"),
  WRONG_VOLATILE_TYPE(
      400,
      "Volatile data must be a ConcurrentHashMap<String, Object>");

  private final int code;
  private final String message;

  KuzzleExceptionCode(final int code) {
    this.code = code;
    this.message = null;
  }

  KuzzleExceptionCode(final int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
