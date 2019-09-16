package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.CustomMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AuthController extends BaseController {

    public AuthController(Kuzzle kuzzle) {
        super(kuzzle);
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> checkToken(String token)
            throws NotConnectedException, InternalException {
        CustomMap query = new CustomMap();

        query.put("controller", "auth");
        query.put("action", "checkToken");
        query.put("body",
                        new CustomMap().put("token", token)
                );

        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (ConcurrentHashMap<String, Object>)response.result
                );
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> createMyCredentials(
            String strategy,
            ConcurrentHashMap<String, Object> credentials
    ) throws NotConnectedException, InternalException {

        CustomMap query = new CustomMap();

        query.put("controller", "auth");
        query.put("action", "createMyCredentials");
        query.put("body", credentials);
        query.put("strategy", strategy);

        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (ConcurrentHashMap<String, Object>)response.result
                );
    }

    public CompletableFuture<Boolean> credentialsExist(String strategy)
            throws NotConnectedException, InternalException {
        CustomMap query = new CustomMap();

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
        CustomMap query = new CustomMap();

        query.put("controller", "auth");
        query.put("action", "deleteMyCredentials");
        query.put("strategy", strategy);


        return kuzzle.query(query).thenRunAsync(() -> {});
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> getCurrentUser()
            throws NotConnectedException, InternalException {
        CustomMap query = new CustomMap();

        query.put("controller", "auth");
        query.put("action", "getCurrentUser");


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (ConcurrentHashMap<String, Object>)response.result
                );
    }

    public CompletableFuture<ConcurrentHashMap<String, Object>> getMyCredentials()
            throws NotConnectedException, InternalException {
        CustomMap query = new CustomMap();

        query.put("controller", "auth");
        query.put("action", "getMyCredentials");


        return kuzzle.query(query)
                .thenApplyAsync((response) ->
                        (ConcurrentHashMap<String, Object>)response.result
                );
    }
}

