package io.kuzzle.sdk;

import io.kuzzle.sdk.CoreClasses.Json.JsonSerializer;
import io.kuzzle.sdk.CoreClasses.Maps.CustomMap;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.*;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import io.kuzzle.sdk.CoreClasses.Responses.*;

import static io.kuzzle.sdk.Helpers.Default.notNull;

public class Kuzzle {

    protected EventListener tokenExpiredEvent;
    protected EventListener<Response> unhandledResponseEvent;

    protected final AbstractProtocol networkProtocol;

    public final String version;
    public final String instanceId;

    /**
     * Authentication token
     */
    protected String authenticationToken;


    /**
     * The maximum amount of elements that the queue can contains.
     * If set to -1, the size is unlimited.
     */
    protected int maxQueueSize;


    /**
     * The minimum duration of a Token before being automatically refreshed.
     * If set to -1 the SDK does not refresh the token automatically.
     */
    protected int minTokenDuration;


    /**
     * The minimum duration of a Token after refresh.
     * If set to -1 the SDK does not refresh the token automatically.
     */
    protected int refreshedTokenDuration;

    /**
     * The maximum delay between two requests to be replayed
     */
    protected int maxRequestDelay;

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
            throw new IllegalArgumentException("newtorkProtocol can't be null");
        }

        KuzzleOptions kOptions = options != null
                ? options
                : new KuzzleOptions();

        this.networkProtocol = networkProtocol;
        this.networkProtocol.registerResponseEvent(this::onResponseReceived);
        this.networkProtocol.registerStateChangeEvent(this::onStateChanged);

        this.maxQueueSize = kOptions.getMaxQueueSize();
        this.minTokenDuration = kOptions.getMinTokenDuration();
        this.refreshedTokenDuration = kOptions.getRefreshedTokenDuration();
        this.maxRequestDelay = kOptions.getMaxRequestDelay();

        this.version = "2.0";
        this.instanceId = UUID.randomUUID().toString();

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
        response.fromMap(JsonSerializer.deserialize(payload));

        if (response.room != null
            && requests.containsKey(notNull(response.room, ""))
        ) {
            if (response.error != null) {
                if (response.error.message != null
                    && response.error.message.equals("Token expired")
                ) {
                    tokenExpiredEvent.trigger();
                }

                Task<Response> task = requests.get(
                        notNull(response.requestId, "")
                );
                if (task != null) {
                    task.setException(new ApiErrorException(response));
                }

            } else {
                Task<Response> task = requests.get(
                        notNull(response.requestId, "")
                );

                if (task != null) {
                    task.trigger(response);
                }

                requests.remove(notNull(response.requestId, ""));
            }
        } else {
            unhandledResponseEvent.trigger(response);
        }
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
            throw new InternalException("You must provide a query", 400);
        }

        if (networkProtocol.getState() == ProtocolState.CLOSE) {
            throw new NotConnectedException();
        }

        CustomMap queryMap = CustomMap.getCustomMap(query);

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
            queryMap.put("volatile", new CustomMap());
        } else if (!queryMap.isMap("volatile")) {
            throw new InternalException("Volatile data must be a ConcurrentHashMap<String, Object>", 400);
        }

        queryMap.getMap("volatile")
             .put("sdkVersion", version);

        queryMap.getMap("volatile")
             .put("sdkInstanceId", instanceId);

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
        return authenticationToken;
    }

    /** Set the authentication token
     * @param token Authentication token
     */
    public void setAuthenticationToken(String token) {
        authenticationToken = token;
    }
}
