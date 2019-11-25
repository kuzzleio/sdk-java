package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.API.Controllers.BaseController;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class AuthController extends BaseController {

    protected EventListener<String> userLoggedInEvent;
    protected EventListener userLoggedOutEvent;

    public AuthController(Kuzzle kuzzle) {
        super(kuzzle);
        userLoggedInEvent = new EventListener<>();
        userLoggedOutEvent = new EventListener();
    }

    /** Registers a callback to be called when the user logs in
     * @param callback A callback
     * @return true if success
     */
    public boolean registerUserLoggedInEvent(Consumer<String> callback) {
        return userLoggedInEvent.register(callback);
    }

    /** Registers a callback to be called when the user logs out
     * @param callback A callback
     * @return true if success
     */
    public boolean registerUserLoggedOutEvent(Runnable callback) {
        return userLoggedOutEvent.register(callback);
    }

    /** Unregisters a previously registered callback for the UserLoggedIn event
     * @param callback A callback
     * @return true if success
     */
    public boolean unregisterUserLoggedInEvent(Consumer<String> callback) {
        return userLoggedInEvent.unregister(callback);
    }

    /** Unregisters a previously registered callback for the UserLoggedOut event
     * @param callback A callback
     * @return true if success
     */
    public boolean unregisterUserLoggedOutEvent(Consumer<String> callback) {
        return userLoggedInEvent.unregister(callback);
    }

    /**
     * Checks the validity of an authentication token.
     * @param token An authentication token
     * @return a CompletableFuture
     * @throws NotConnectedException
     * @throws InternalException
     */
    public CompletableFuture<ConcurrentHashMap<String, Object>> checkToken(String token)
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "checkToken");
        query.put("body",
                new KuzzleMap().put("token", token));

        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        KuzzleMap.getKuzzleMap(
                                (ConcurrentHashMap<String, Object>) response.result)
                );
    }

    /**
     * Creates new credentials for the current user.
     * @param strategy A String representing the strategy
     * @param credentials A ConcurrentHashMap<String, Object> representing credentials information
     * @return A CompletableFuture
     * @throws NotConnectedException
     * @throws InternalException
     */
    public CompletableFuture<ConcurrentHashMap<String, Object>> createMyCredentials(
            String strategy,
            ConcurrentHashMap<String, Object> credentials
    ) throws NotConnectedException, InternalException {

        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "createMyCredentials");
        query.put("body", credentials);
        query.put("strategy", strategy);

        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        KuzzleMap.getKuzzleMap(
                                (ConcurrentHashMap<String, Object>) response.result)
                );
    }

    /**
     * Checks that the current authenticated user has credentials for the
     * specified authentication strategy.
     * @param strategy A String representing the strategy
     * @return A CompletableFuture
     * @throws NotConnectedException
     * @throws InternalException
     */
    public CompletableFuture<Boolean> credentialsExist(String strategy)
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "credentialsExist");
        query.put("strategy", strategy);


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (Boolean)response.result
                );
    }


    public CompletableFuture<Void> deleteMyCredentials(String strategy)
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "deleteMyCredentials");
        query.put("strategy", strategy);


        return kuzzle.query(query).thenRunAsync(() -> {});
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> getCurrentUser()
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "getCurrentUser");


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        KuzzleMap.getKuzzleMap(
                                (ConcurrentHashMap<String, Object>) response.result)
                );
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> getMyCredentials()
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "getMyCredentials");


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        KuzzleMap.getKuzzleMap(
                                (ConcurrentHashMap<String, Object>) response.result)
                );
    }

    public CompletableFuture<ArrayList<Object>> getMyRightsAsync()
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "getMyRights");


        return kuzzle.query(query)
                .thenApplyAsync((response) -> {
                    KuzzleMap map = KuzzleMap.getKuzzleMap(
                            (ConcurrentHashMap<String, Object>) response.result);
                    return map.getArrayList("hits");
                });
    }

    public CompletableFuture<ArrayList<Object>> getStrategiesAsync()
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "getStrategies");


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (ArrayList<Object>)response.result
                );
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> login(
            String strategy,
            ConcurrentHashMap<String, Object> credentials,
            Long expiresIn
    ) throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "login");
        query.put("strategy", strategy);
        query.put("body", credentials);
        query.put("expiresIn", expiresIn);


        return kuzzle.query(query)
                .thenApplyAsync((response) -> {
                    KuzzleMap map = KuzzleMap.getKuzzleMap(
                            (ConcurrentHashMap<String, Object>) response.result);
                    kuzzle.setAuthenticationToken(map.getString("jwt"));
                    if (!map.isNull("_id")) {
                        userLoggedInEvent.trigger(map.get("_id").toString());
                    }
                    return map;
                });
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> login(
            String strategy,
            ConcurrentHashMap<String, Object> credentials
    ) throws NotConnectedException, InternalException {
        return login(strategy, credentials, null);
    }

    public CompletableFuture<Void> logout()
            throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "logout");


        return kuzzle.query(query)
                .thenRunAsync(() -> {
                    userLoggedOutEvent.trigger();
                });
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken(
            Long expiresIn
    ) throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "refreshToken");
        query.put("expiresIn", expiresIn);


        return kuzzle.query(query)
                .thenApplyAsync((response) -> {
                    KuzzleMap map = KuzzleMap.getKuzzleMap(
                            (ConcurrentHashMap<String, Object>) response.result);
                    kuzzle.setAuthenticationToken(map.getString("jwt"));

                    return map;
                });
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken()
            throws NotConnectedException, InternalException {
        return refreshToken(null);
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> updateMyCredentials(
            String strategy,
            ConcurrentHashMap<String, Object> credentials
    ) throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "updateMyCredentials");
        query.put("strategy", strategy);
        query.put("body", credentials);


        return kuzzle.query(query)
                .thenApplyAsync((response) -> {
                    KuzzleMap map = KuzzleMap.getKuzzleMap(
                            (ConcurrentHashMap<String, Object>) response.result);

                    return map;
                });
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> updateSelf(
            ConcurrentHashMap<String, Object> content
    ) throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "updateSelf");
        query.put("body", content);


        return kuzzle.query(query)
                .thenApplyAsync((response) -> {
                    KuzzleMap map = KuzzleMap.getKuzzleMap(
                            (ConcurrentHashMap<String, Object>) response.result);

                    return map;
                });
    }

    public CompletableFuture<Boolean> validateMyCredentials(
            String strategy,
            ConcurrentHashMap<String, Object> credentials
    ) throws NotConnectedException, InternalException {
        KuzzleMap query = new KuzzleMap();

        query.put("controller", "auth");
        query.put("action", "validateMyCredentials");
        query.put("strategy", strategy);
        query.put("body", credentials);


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (Boolean)response.result
                );
    }

}