package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AuthController extends BaseController {

  public AuthController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Checks the validity of an authentication token.
   *
   * @param token An authentication token
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> checkToken(
      final String token) throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "checkToken")
        .put("body", new KuzzleMap().put("token", token));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Creates new credentials for the current user.
   *
   * @param strategy    A String representing the strategy
   * @param credentials A ConcurrentHashMap<String, Object> representing
   *                    credentials information
   * @return A CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> createMyCredentials(
      final String strategy,
      final ConcurrentHashMap<String, Object> credentials)
      throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "createMyCredentials")
        .put("body", credentials)
        .put("strategy", strategy);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result));
  }

  /**
   * Checks that the current authenticated user has credentials for the
   * specified authentication strategy.
   *
   * @param strategy A String representing the strategy
   * @return A CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> credentialsExist(final String strategy)
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "credentialsExist")
        .put("strategy", strategy);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> (Boolean) response.result);
  }

  public CompletableFuture<Void> deleteMyCredentials(final String strategy)
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query.put("controller", "auth");
    query.put("action", "deleteMyCredentials");
    query.put("strategy", strategy);

    return kuzzle.query(query).thenRunAsync(() -> {
    });
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> getCurrentUser()
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query.put("controller", "auth").put("action", "getCurrentUser");

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result));
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> getMyCredentials(
      final String strategy) throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "getMyCredentials")
        .put("strategy", strategy);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result));
  }

  public CompletableFuture<ArrayList<Object>> getMyRights()
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query.put("controller", "auth").put("action", "getMyRights");

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result)
            .getArrayList("hits"));
  }

  public CompletableFuture<ArrayList<String>> getStrategies()
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query.put("controller", "auth")
        .put("action", "getStrategies");

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> (ArrayList<String>) response.result);
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> login(
      final String strategy,
      final ConcurrentHashMap<String, Object> credentials,
      final String expiresIn) throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "login")
        .put("strategy", strategy)
        .put("body", credentials)
        .put("expiresIn", expiresIn);

    return kuzzle.query(query).thenApplyAsync((response) -> {
      final KuzzleMap map = KuzzleMap
          .from((ConcurrentHashMap<String, Object>) response.result);
      kuzzle.setAuthenticationToken(map.getString("jwt"));
      if (map.getString("_id") != null) {
        kuzzle.trigger(Event.loginAttempt, true);
      } else {
        kuzzle.trigger(Event.loginAttempt, false);
      }
      return map;
    });
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> login(
      final String strategy,
      final ConcurrentHashMap<String, Object> credentials)
      throws NotConnectedException, InternalException {
    return login(strategy, credentials, null);
  }

  public CompletableFuture<Response> logout()
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query.put("controller", "auth").put("action", "logout");

    return kuzzle.query(query);
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken(
      final String expiresIn) throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "refreshToken")
        .put("expiresIn", expiresIn);

    return kuzzle.query(query).thenApplyAsync((response) -> {
      final KuzzleMap map = KuzzleMap
          .from((ConcurrentHashMap<String, Object>) response.result);
      kuzzle.setAuthenticationToken(map.getString("jwt"));

      return map;
    });
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken()
      throws NotConnectedException, InternalException {
    return refreshToken(null);
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> updateMyCredentials(
      final String strategy,
      final ConcurrentHashMap<String, Object> credentials)
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "updateMyCredentials")
        .put("strategy", strategy)
        .put("body", credentials);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result));
  }

  public CompletableFuture<ConcurrentHashMap<String, Object>> updateSelf(
      final ConcurrentHashMap<String, Object> content)
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "updateSelf")
        .put("body", content);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> KuzzleMap
            .from((ConcurrentHashMap<String, Object>) response.result));
  }

  public CompletableFuture<Boolean> validateMyCredentials(final String strategy,
                                                          final ConcurrentHashMap<String, Object> credentials)
      throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("controller", "auth")
        .put("action", "validateMyCredentials")
        .put("strategy", strategy)
        .put("body", credentials);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> (Boolean) response.result);
  }
}
