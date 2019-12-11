package io.kuzzle.sdk;

import io.kuzzle.sdk.CoreClasses.Json.JsonSerializer;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.*;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import io.kuzzle.sdk.CoreClasses.Responses.*;

import static io.kuzzle.sdk.Helpers.Default.defaultValue;

public class Kuzzle {

    protected EventListener tokenExpiredEvent;
    protected EventListener<Response> unhandledResponseEvent;

    protected final AbstractProtocol networkProtocol;

    public final String version;
    public final String instanceId;
    public final String sdkName;

    /**
     * Authentication token
     */
    protected AtomicReference<String> authenticationToken;


    /**
     * The maximum amount of elements that the queue can contains.
     * If set to -1, the size is unlimited.
     */
    protected AtomicInteger maxQueueSize;


    /**
     * The minimum duration of a Token before being automatically refreshed.
     * If set to -1 the SDK does not refresh the token automatically.
     */
    protected AtomicInteger minTokenDuration;


    /**
     * The minimum duration of a Token after refresh.
     * If set to -1 the SDK does not refresh the token automatically.
     */
    protected AtomicInteger refreshedTokenDuration;

    /**
     * The maximum delay between two requests to be replayed
     */
    protected AtomicInteger maxRequestDelay;

    protected ConcurrentHashMap<String, Task<Response>>
                    requests = new ConcurrentHashMap<>();

    /** Initialize a new instance of Kuzzle
     * @param networkProtocol   The network protocol
     * @throws IllegalArgumentException
     */
    public Kuzzle(AbstractProtocol networkProtocol)
            throws IllegalArgumentException {
        this(networkProtocol, new KuzzleOptions());
    }

    /** Initialize a new instance of Kuzzle
     * @param networkProtocol   The network protocol
     * @param options           Kuzzle options
     * @throws IllegalArgumentException
     */
    public Kuzzle(
            AbstractProtocol networkProtocol,
            final KuzzleOptions options
    ) throws IllegalArgumentException {

        if (networkProtocol == null) {
            throw new IllegalArgumentException("networkProtocol can't be null");
        }

        KuzzleOptions kOptions = options != null
                ? options
                : new KuzzleOptions();

        this.networkProtocol = networkProtocol;
        this.networkProtocol.registerResponseEvent(this::onResponseReceived);
        this.networkProtocol.registerStateChangeEvent(this::onStateChanged);

        this.maxQueueSize = new AtomicInteger(kOptions.getMaxQueueSize());
        this.minTokenDuration = new AtomicInteger(kOptions.getMinTokenDuration());
        this.refreshedTokenDuration = new AtomicInteger(kOptions.getRefreshedTokenDuration());
        this.maxRequestDelay = new AtomicInteger(kOptions.getMaxRequestDelay());

        this.version = "3.0.0";
        this.instanceId = UUID.randomUUID().toString();
        this.sdkName = "java@"+version;

        this.tokenExpiredEvent = new EventListener();
        this.unhandledResponseEvent = new EventListener<>();
    }

    /** Establish a network connection
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

    /** Handles the ResponseReceivedEvent from the network protocol
     * @param payload Raw API Response
     */
    protected void onResponseReceived(String payload) {

        Response response = new Response();
        try {
            response.fromMap(JsonSerializer.deserialize(payload));
        } catch (InternalException e) {
            e.printStackTrace();
            return;
        }

        if (response.room == null
            || !requests.containsKey(response.room)
        ) {
            unhandledResponseEvent.trigger(response);
            return;
        }

        if (response.error == null) {
            Task<Response> task = requests.get(response.requestId);

            if (task != null) {
                task.trigger(response);
            }

            requests.remove(response.requestId);
            return;
        }

        if (response.error.id == null
                || !response.error.id.equals("security.token.expired")
        ) {
            Task<Response> task = requests.get(response.requestId);
            if (task != null) {
                task.setException(new ApiErrorException(response));
            }
            return;
        }

        tokenExpiredEvent.trigger();
    }

    protected void onStateChanged(ProtocolState state) {
        // If not connected anymore: close tasks and clean up the requests buffer
        if (state == ProtocolState.CLOSE) {
            for (Task task : requests.values()) {
                task.setException(new ConnectionLostException());
            }
            requests.clear();
        }
    }

    /** Registers a callback to be called when the token expires
     * @param callback A callback
     * @return true if success
     */
    public boolean registerTokenExpiredEvent(Runnable callback) {
        return tokenExpiredEvent.register(callback);
    }

    /** Unregisters a previously registered callback for the TokenExpired event
     * @param callback A callback
     * @return true if success
     */
    public boolean unregisterTokenExpiredEvent(Runnable callback) {
        return tokenExpiredEvent.unregister(callback);
    }

    /** Registers a callback to be called when a response is unhandled
     * @param callback  A callback
     * @return true if success
     */
    public boolean registerUnhandledResponseEvent(Consumer<Response> callback) {
        return unhandledResponseEvent.register(callback);
    }

    /**
     * @param callback Unregisters a previously registered callback for the UnhandledResponse event
     * @return true if success
     */
    public boolean unregisterUnhandledResponseEvent(Consumer<Response> callback) {
        return unhandledResponseEvent.unregister(callback);
    }

    /**
     * Triggers the TokenExpired event
     */
    public void dispatchTokenExpired() {
        tokenExpiredEvent.trigger();
    }

    /** Sends an API request to Kuzzle and returns the corresponding API
     * @param query Kuzzle API query
     * @return A CompletableFuture
     * @throws InternalException
     * @throws NotConnectedException
     */
    public CompletableFuture<Response> query(ConcurrentHashMap<String, Object> query)
            throws InternalException, NotConnectedException {
        if (query == null) {
            throw new InternalException(KuzzleExceptionCode.MSSING_QUERY);
        }

        if (networkProtocol.getState() == ProtocolState.CLOSE) {
            throw new NotConnectedException();
        }

        KuzzleMap queryMap = KuzzleMap.from(query);

        if (queryMap.contains("waitForRefresh")) {
            if (queryMap.optBoolean("waitForRefresh", false).booleanValue()) {
                queryMap.put("refresh", "wait_for");
            }
            queryMap.remove("waitForRefresh");
        }

        if (authenticationToken != null) {
            queryMap.put("jwt", authenticationToken);
        }

        String requestId = UUID.randomUUID().toString();

        queryMap.put("requestId", requestId);

        if (!queryMap.containsKey("volatile")
            || queryMap.isNull("volatile")
        ) {
            queryMap.put("volatile", new KuzzleMap());
        } else if (!queryMap.isMap("volatile")) {
            throw new InternalException(KuzzleExceptionCode.WRONG_VOLATILE_TYPE);
        }

        queryMap.getMap("volatile")
             .put("sdkVersion", version);

        queryMap.getMap("volatile")
             .put("sdkInstanceId", instanceId);

        queryMap.getMap("volatile")
                .put("sdkName", sdkName);

        Task<Response> task = new Task<>();
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

    /** Set the authentication token
     * @param token Authentication token
     */
    public void setAuthenticationToken(String token) {
        authenticationToken.set(token);
    }
}
