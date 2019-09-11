package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.AbstractKuzzle;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Helpers.IJObjectHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class AuthController<T> extends BaseController<T> {

    public AuthController(AbstractKuzzle<T> kuzzle) {
        super(kuzzle);
    }

    public CompletableFuture<T> checkToken(String token)
            throws NotConnectedException, InternalException {
        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "checkToken")
                .put("body",
                        jsonObject.newIJObject().put("token", token)
                );

        return kuzzle.query(jsonObject.toNative())
                .thenApplyAsync((response) ->
                    response.result.toNative()
                );
    }

    public CompletableFuture<T> createMyCredentials(
            String strategy,
            T credentials
    ) throws NotConnectedException, InternalException {

        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "createMyCredentials")
                .put("body", credentials)
                .put("strategy", strategy);

        return kuzzle.query(jsonObject.toNative())
                .thenApplyAsync((response) ->
                        response.result.toNative()
                );
    }

    public CompletableFuture<Boolean> credentialsExist(String strategy)
            throws NotConnectedException, InternalException {
        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "credentialsExist")
                .put("strategy", strategy);


        return kuzzle.query(jsonObject.toNative())
                .thenApplyAsync((response) ->
                        response.result.toBoolean()
                );
    }

    public CompletableFuture<Void> deleteMyCredentials(String strategy)
            throws NotConnectedException, InternalException {
        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "deleteMyCredentials")
                .put("strategy", strategy);


        return kuzzle.query(jsonObject.toNative()).thenRunAsync(() -> {});
    }

    public CompletableFuture<T> getCurrentUser()
            throws NotConnectedException, InternalException {
        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "getCurrentUser");


        return kuzzle.query(jsonObject.toNative())
                .thenApplyAsync((response) -> response.result.toNative());
    }

    public CompletableFuture<T> getMyCredentials()
            throws NotConnectedException, InternalException {
        IJObject<T> jsonObject = IJObjectHelper.newIJObject();

        jsonObject.put("controller", "auth")
                .put("action", "getMyCredentials");


        return kuzzle.query(jsonObject.toNative())
                .thenApplyAsync((response) -> response.result.toNative());
    }
}

