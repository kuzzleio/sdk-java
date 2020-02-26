package io.kuzzle.sdk;

import io.kuzzle.sdk.API.Controllers.IndexController;
import io.kuzzle.sdk.API.Controllers.RealtimeController;
import io.kuzzle.sdk.CoreClasses.Json.JsonSerializer;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.API.Controllers.AuthController;
import io.kuzzle.sdk.API.Controllers.DocumentController;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Exceptions.*;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.CoreClasses.Responses.*;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Events.EventManager;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Kuzzle extends EventManager {
  protected final AbstractProtocol networkProtocol;

  public final String version;
  public final String instanceId;
  public final String sdkName;

  /**
   * Authentication token
   */
  protected AtomicReference<String> authenticationToken;

  /**
   * The maximum amount of elements that the queue can contains. If set to -1,
   * the size is unlimited.
   */
  protected AtomicInteger maxQueueSize;

  /**
   * The minimum duration of a Token before being automatically refreshed. If
   * set to -1 the SDK does not refresh the token automatically.
   */
  protected AtomicInteger minTokenDuration;

  /**
   * The minimum duration of a Token after refresh. If set to -1 the SDK does
   * not refresh the token automatically.
   */
  protected AtomicInteger refreshedTokenDuration;

  /**
   * The maximum delay between two requests to be replayed
   */
  protected AtomicInteger maxRequestDelay;

  protected ConcurrentHashMap<String, Task<Response>> requests = new ConcurrentHashMap<>();

  private RealtimeController realtimeController;

  /**
   * Initialize a new instance of Kuzzle
   *
   * @param networkProtocol The network protocol
   * @throws IllegalArgumentException
   */
  public Kuzzle(final AbstractProtocol networkProtocol)
      throws IllegalArgumentException {
    this(networkProtocol, new KuzzleOptions());
  }

  /**
   * @return The AuthController
   */
  public AuthController getAuthController() {
    return new AuthController(this);
  }

  public DocumentController getDocumentController() {
    return new DocumentController(this);
  }

  /**
   * @return The IndexController
   */
  public IndexController getIndexController() {
    return new IndexController(this);
  }

  /**
   * @return RealtimeController
   */
  public RealtimeController getRealtimeController() {
    if (this.realtimeController == null) {
      synchronized (Kuzzle.class) {
        if (this.realtimeController == null) {
          this.realtimeController = new RealtimeController(this);
        }
      }
    }
    return this.realtimeController;
  }

  /**
   * Initialize a new instance of Kuzzle
   *
   * @param networkProtocol The network protocol
   * @param options         Kuzzle options
   * @throws IllegalArgumentException
   */
  public Kuzzle(final AbstractProtocol networkProtocol,
                final KuzzleOptions options) throws IllegalArgumentException {
    if (networkProtocol == null) {
      throw new IllegalArgumentException("networkProtocol can't be null");
    }

    final KuzzleOptions kOptions = options != null ? options
        : new KuzzleOptions();

    this.networkProtocol = networkProtocol;
    this.networkProtocol
        .register(Event.networkResponseReceived, this::onResponseReceived);
    this.networkProtocol
        .register(Event.networkStateChange, this::onStateChanged);

    this.maxQueueSize = new AtomicInteger(kOptions.getMaxQueueSize());
    this.minTokenDuration = new AtomicInteger(kOptions.getMinTokenDuration());
    this.refreshedTokenDuration = new AtomicInteger(
        kOptions.getRefreshedTokenDuration());
    this.maxRequestDelay = new AtomicInteger(kOptions.getMaxRequestDelay());

    this.version = "3";
    this.instanceId = UUID.randomUUID().toString();
    this.sdkName = "java@" + version;
  }

  /**
   * Establish a network connection
   *
   * @throws Exception
   */
  public void connect() throws Exception {
    networkProtocol.connect();
  }

  /**
   * Disconnect this instance
   */
  public void disconnect() {
    networkProtocol.disconnect();
  }

  /**
   * Handles the ResponseReceivedEvent from the network protocol
   *
   * @param payload Raw API Response
   */
  protected void onResponseReceived(final Object... payload) {
    final Response response = new Response();
    try {
      response.fromMap(JsonSerializer.deserialize(payload[0].toString()));
    } catch (final Exception e) {
      e.printStackTrace();
      return;
    }

    if (requests != null && (response.room == null || !requests.containsKey(response.room))) {
      super.trigger(Event.unhandledResponse, response);
      return;
    }

    if (response.error == null) {
      final Task<Response> task = requests.get(response.requestId);

      if (task != null) {
        task.trigger(response);
      }

      requests.remove(response.requestId);
      return;
    }

    if (response.error.id == null
        || !response.error.id.equals("security.token.expired")) {
      final Task<Response> task = requests.get(response.requestId);
      if (task != null) {
        task.setException(new ApiErrorException(response));
      }
      return;
    }

    super.trigger(Event.tokenExpired);
  }

  protected void onStateChanged(final Object... args) {
    // If not connected anymore: close tasks and clean up the requests buffer
    if ((ProtocolState) args[0] == ProtocolState.CLOSE) {
      for (final Task<Response> task : requests.values()) {
        task.setException(new ConnectionLostException());
      }
      requests.clear();
    }
  }

  /**
   * Sends an API request to Kuzzle and returns the corresponding API
   *
   * @param query Kuzzle API query
   * @return A CompletableFuture
   * @throws InternalException
   * @throws NotConnectedException
   */
  public CompletableFuture<Response> query(
      final ConcurrentHashMap<String, Object> query)
      throws InternalException, NotConnectedException {
    if (query == null) {
      throw new InternalException(KuzzleExceptionCode.MISSING_QUERY);
    }


    if (networkProtocol.getState() == ProtocolState.CLOSE) {
      throw new NotConnectedException();
    }
    final KuzzleMap queryMap = KuzzleMap.from(query);

    if (queryMap.contains("waitForRefresh")) {
      if (queryMap.optBoolean("waitForRefresh", false).booleanValue()) {
        queryMap.put("refresh", "wait_for");
      }
      queryMap.remove("waitForRefresh");
    }

    if (authenticationToken != null) {
      queryMap.put("jwt", authenticationToken.toString());
    }

    final String requestId = UUID.randomUUID().toString();

    queryMap.put("requestId", requestId);

    if (!queryMap.containsKey("volatile") || queryMap.isNull("volatile")) {
      queryMap.put("volatile", new KuzzleMap());
    } else if (!queryMap.isMap("volatile")) {
      throw new InternalException(KuzzleExceptionCode.WRONG_VOLATILE_TYPE);
    }

    queryMap.getMap("volatile").put("sdkInstanceId", instanceId);

    queryMap.getMap("volatile").put("sdkName", sdkName);

    final Task<Response> task = new Task<>();
    requests.put(requestId, task);

    if (networkProtocol.getState() == ProtocolState.OPEN) {
      networkProtocol.send(queryMap);
    }

    return task.getFuture();
  }

  /**
   * @return The authentication token
   */
  public String getAuthenticationToken() {
    return authenticationToken.get();
  }

  /**
   * Set the authentication token
   *
   * @param token Authentication token
   */
  public void setAuthenticationToken(final String token) {
    if (authenticationToken == null) {
      authenticationToken = new AtomicReference<>();
    }
    authenticationToken.set(token);
  }
}
