package io.kuzzle.sdk.CoreClasses.Responses;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Maps.Serializable;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.KuzzleException;
import io.kuzzle.sdk.Exceptions.KuzzleExceptionCode;

import java.util.concurrent.ConcurrentHashMap;

public class Response implements Serializable {

  public String room;

  /**
   * Response payload (depends on the executed API action)
   */
  public Object result;

  /**
   * Error object (null if the request finished successfully)
   */
  public ErrorResponse error;

  /**
   * Request unique identifier.
   */
  public String requestId;

  /**
   * Response status, following HTTP status codes
   */
  public int status;

  /**
   * Executed Kuzzle API controller.
   */
  public String controller;

  /**
   * Executed Kuzzle API controller's action.
   */
  public String action;

  /**
   * Impacted data index.
   */
  public String index;

  /**
   * Impacted data collection.
   */
  public String collection;

  /**
   * Volatile data.
   */
  public ConcurrentHashMap<String, Object> Volatile;

  // The following properties are specific to real-time notifications

  /**
   * Network protocol at the origin of the real-time notification.
   */
  public String protocol;

  /**
   * Document scope ("in" or "out")
   */
  public String scope;

  /**
   * Document state
   */
  public String state;

  /**
   * Notification timestamp (UTC)
   */
  public Long timestamp;

  /**
   * Notification type
   */
  public String type;

  @Override
  public void fromMap(ConcurrentHashMap<String, Object> map) throws InternalException {
    if (map == null)
      return;

    KuzzleMap kuzzleMap = KuzzleMap.from(map);

    room = kuzzleMap.getString("room");
    result = kuzzleMap.get("result");
    error = null;
    if (kuzzleMap.isMap("error")) {
      error = new ErrorResponse();
      error.fromMap(kuzzleMap.getMap("error"));
    }
    requestId = kuzzleMap.getString("requestId");
    if (requestId == null) {
      throw new InternalException(KuzzleExceptionCode.MISSING_REQUESTID);
    }
    status = kuzzleMap.optNumber("status", 0).intValue();
    controller = kuzzleMap.getString("controller");
    action = kuzzleMap.getString("action");
    index = kuzzleMap.getString("index");
    collection = kuzzleMap.getString("collection");
    Volatile = kuzzleMap.optMap("volatile", new ConcurrentHashMap<>());
    protocol = kuzzleMap.getString("protocol");
    scope = kuzzleMap.getString("scope");
    state = kuzzleMap.getString("state");
    timestamp = (Long) kuzzleMap.getNumber("timestamp");
    type = kuzzleMap.getString("type");
  }

  @Override
  public ConcurrentHashMap<String, Object> toMap() {
    ConcurrentHashMap<String, Object> map = new KuzzleMap();

    map.put("room", room);
    map.put("result", result);
    map.put("error", error);
    map.put("requestId", requestId);
    map.put("status", status);
    map.put("controller", controller);
    map.put("action", action);
    map.put("index", index);
    map.put("collection", collection);
    map.put("volatile", Volatile);
    map.put("protocol", protocol);
    map.put("scope", scope);
    map.put("status", status);
    map.put("timestamp", timestamp);
    map.put("type", type);

    return map;
  }
}
