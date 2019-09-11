package io.kuzzle.sdk;

import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.*;
import io.kuzzle.sdk.Helpers.IJObjectHelper;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;

import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import io.kuzzle.sdk.CoreClasses.Responses.*;

import static io.kuzzle.sdk.Helpers.Default.notNull;

/**
 * @param <T> The json object of the Json library you want to use.
 */
public abstract class AbstractKuzzle<T> {

    protected EventListener tokenExpiredEvent;
    protected EventListener<Response> unhandledResponseEvent;

    protected final AbstractProtocol<T> networkProtocol;

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

    protected ConcurrentHashMap<String, Task<Response<T>>>
                    requests = new ConcurrentHashMap<>();

    /** Initialize a new instance of Kuzzle
     * @param jobject           An instance of an object implementing IJObject
     * @param networkProtocol   The network protocol
     * @throws IllegalArgumentException
     */
    public AbstractKuzzle(IJObject<T> jobject, AbstractProtocol<T> networkProtocol)
            throws IllegalArgumentException {
        this(jobject, networkProtocol, new KuzzleOptions());
    }

    /** Initialize a new instance of Kuzzle
     * @param jobject           An instance of an object implementing IJObject
     * @param networkProtocol   The network protocol
     * @param options           Kuzzle options
     * @throws IllegalArgumentException
     */
    public AbstractKuzzle(
            IJObject<T>  jobject,
            AbstractProtocol<T> networkProtocol,
            final KuzzleOptions options
    ) throws IllegalArgumentException {

        if (jobject == null) {
            throw new IllegalArgumentException("jobject can't be null");
        }

        if (networkProtocol == null) {
            throw new IllegalArgumentException("newtorkProtocol can't be null");
        }

        IJObjectHelper.init(jobject);

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
        Response<T> response = new Response<>();
        response.fromIJObject(IJObjectHelper.parse(payload));

        if (response.room != null
            && requests.containsKey(notNull(response.room, ""))
        ) {
            if (response.error != null) {
                if (response.error.message != null
                    && response.error.message.equals("Token expired")
                ) {
                    tokenExpiredEvent.trigger();
                }

                Task<Response<T>> task = requests.get(
                        notNull(response.requestId, "")
                );
                if (task != null) {
                    task.setException(new ApiErrorException(response));
                }

            } else {
                Task<Response<T>> task = requests.get(
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
    public CompletableFuture<Response<T>> query(T query)
            throws InternalException, NotConnectedException {
        if (query == null) {
            throw new InternalException("You must provide a query", 400);
        }

        if (networkProtocol.getState() == ProtocolState.CLOSE) {
            throw new NotConnectedException();
        }

        IJObject<T> queryJObject = IJObjectHelper.newIJObject(query);

        if (queryJObject.has("waitForRefresh")) {
            if (queryJObject.getBoolean("waitForRefresh")) {
                queryJObject.put("refresh", "wait_for");
            }
            queryJObject.remove("waitForRefresh");
        }

        if (authenticationToken != null) {
            queryJObject.put("jwt", authenticationToken);
        }

        String requestId = UUID.randomUUID().toString();

        queryJObject.put("requestId", requestId);

        if (!queryJObject.has("volatile")
            || queryJObject.isNull("volatile")
        ) {
            queryJObject.put("volatile", IJObjectHelper.newIJObject());
        } else if (!queryJObject.isJsonObject("volatile")) {
            throw new InternalException("Volatile data must be a JsonObject", 400);
        }

        queryJObject.getJsonObject("volatile")
             .put("sdkVersion", version);

        queryJObject.getJsonObject("volatile")
             .put("sdkInstanceId", instanceId);

        Task<Response<T>> task = new Task<>();
        requests.put(requestId, task);

        if (networkProtocol.getState() == ProtocolState.OPEN) {
            networkProtocol.send(queryJObject);
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
