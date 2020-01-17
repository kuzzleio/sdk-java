package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Maps.Serializable;

import java.util.concurrent.ConcurrentHashMap;

public class ErrorResponse implements Serializable {

  public ErrorResponse() {

  }

  /**
   * Response status, following HTTP status codes.
   */
  public int status;

  /**
   * Error message
   */
  public String message;

  /**
   * Error ID
   */
  public String id;

  /**
   * Error stack
   */
  public String stack;

  @Override
  public void fromMap(ConcurrentHashMap<String, Object> map) {
    if (map == null)
      return;

    KuzzleMap kuzzleMap = KuzzleMap.from(map);

    status = kuzzleMap.optNumber("status", 0).intValue();
    message = kuzzleMap.getString("message");
    stack = kuzzleMap.getString("stack");
    id = kuzzleMap.getString("id");
  }

  @Override
  public ConcurrentHashMap<String, Object> toMap() {
    ConcurrentHashMap<String, Object> map = new KuzzleMap();

    map.put("status", status);
    map.put("message", message);
    map.put("stack", stack);
    map.put("id", id);
    return map;
  }
}
