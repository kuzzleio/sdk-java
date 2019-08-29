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

public abstract class AbstractKuzzle<T> {

    protected EventListener tokenExpiredEvent;
    protected EventListener<Response> unhandledResponseEvent;

    protected final AbstractProtocol<T> networkProtocol;

    public final String version;
    public final String instanceId;

    protected int maxQueueSize;
    protected int minTokenDuration;
    protected int refreshedTokenDuration;
    protected int maxRequestDelay;

    protected boolean autoReconnect;
    protected boolean autoReplay;
    protected boolean autoResubscribe;

    protected ConcurrentHashMap<String, Task<Response<T>>>
                    requests = new ConcurrentHashMap<>();

    public AbstractKuzzle(IJObject<T> jobject, AbstractProtocol<T> networkProtocol)
            throws IllegalArgumentException {
        this(jobject, networkProtocol, new KuzzleOptions());
    }

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
        this.networkProtocol.registerMessageEvent(this::onResponseReceived);
        this.networkProtocol.registerStateChange(this::onStateChanged);

        this.maxQueueSize = kOptions.getMaxQueueSize();
        this.minTokenDuration = kOptions.getMinTokenDuration();
        this.refreshedTokenDuration = kOptions.getRefreshedTokenDuration();
        this.maxRequestDelay = kOptions.getMaxRequestDelay();

        this.autoReconnect = kOptions.isAutoReconnected();
        this.autoReplay = kOptions.isAutoReplayed();
        this.autoResubscribe = kOptions.isAutoResubscribed();

        this.version = "2.0";
        this.instanceId = UUID.randomUUID().toString();

        this.tokenExpiredEvent = new EventListener();
        this.unhandledResponseEvent = new EventListener<>();
    }

    public void connect() throws Exception {
        networkProtocol.connect();
    }

    public void disconnect() {
        networkProtocol.disconnect();
    }

    protected void onResponseReceived(String payload) {

        System.out.println(payload);
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
        if (state == ProtocolState.CLOSE) {
            for (Task task : requests.values()) {
                task.setException(new ConnectionLostException());
            }
            requests.clear();
        }
    }

    public boolean registerTokenExpiredEvent(Runnable callback) {
        return tokenExpiredEvent.register(callback);
    }

    public boolean unregisterTokenExpiredEvent(Runnable callback) {
        return tokenExpiredEvent.unregister(callback);
    }

    public boolean registerUnhandledResponseEvent(Consumer<Response> callback) {
        return unhandledResponseEvent.register(callback);
    }

    public boolean unregisterUnhandledResponseEvent(Consumer<Response> callback) {
        return unhandledResponseEvent.unregister(callback);
    }

    public void dispatchTokenExpired() {
        tokenExpiredEvent.trigger();
    }

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

        String requestId = UUID.randomUUID().toString();

        queryJObject.put("requestId", requestId);

        if (!queryJObject.has("volatile")
            || queryJObject.isNull("volatile")
        ) {
            queryJObject.put("volatile", IJObjectHelper.newIJObject());
        } else if (!queryJObject.isIJObject("volatile")) {
            throw new InternalException("Volatile data must be a JsonObject", 400);
        }

        queryJObject.getIJObject("volatile")
             .put("sdkVersion", version);

        queryJObject.getIJObject("volatile")
             .put("sdkInstanceId", instanceId);

        Task<Response<T>> task = new Task<>();
        requests.put(requestId, task);

        if (networkProtocol.getState() == ProtocolState.OPEN) {
            networkProtocol.send(queryJObject);
        }

        return task.getFuture();
    }
}
