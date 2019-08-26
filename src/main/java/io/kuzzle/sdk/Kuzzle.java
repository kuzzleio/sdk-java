package io.kuzzle.sdk;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.ApiErrorException;
import io.kuzzle.sdk.Exceptions.ConnectionLostException;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.CoreClasses.Response.Response;

import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static io.kuzzle.sdk.Helpers.Default.notNull;

public class Kuzzle {

    protected EventListener tokenExpiredEvent;
    protected EventListener<Response> unhandledResponseEvent;

    protected final AbstractProtocol networkProtocol;

    public final String version;
    public final String instanceId;

    protected int maxQueueSize;
    protected int minTokenDuration;
    protected int refreshedTokenDuration;
    protected int maxRequestDelay;

    protected boolean autoReconnect;
    protected boolean autoReplay;
    protected boolean autoResubscribe;

    protected ConcurrentHashMap<String, Task<Response>>
                    requests = new ConcurrentHashMap<>();

    public Kuzzle(@NotNull AbstractProtocol networkProtocol)
            throws URISyntaxException, IllegalArgumentException {
        this(networkProtocol,
             new KuzzleOptions());
    }

    public Kuzzle(
            @NotNull AbstractProtocol networkProtocol,
            final KuzzleOptions options
    ) throws IllegalArgumentException {

        if (networkProtocol == null) {
            throw new IllegalArgumentException("NewtorkProtocol can't be null");
        }

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
        this.unhandledResponseEvent = new EventListener<Response>();
    }

    public void connect() throws Exception {
        networkProtocol.connect();
    }

    public void disconnect() {
        networkProtocol.disconnect();
    }

    protected void onResponseReceived(String payload) {
        Response response = new Gson().fromJson(payload, Response.class);

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

    public CompletableFuture<Response> query(@NotNull JsonObject query)
            throws InternalException, NotConnectedException {
        if (query == null) {
            throw new InternalException("You must provide a query", 400);
        }

        if (networkProtocol.getState() == ProtocolState.CLOSE) {
            throw new NotConnectedException();
        }

        if (query.has("waitForRefresh")) {
            if (query.get("waitForRefresh").getAsBoolean()) {
                query.addProperty("refresh", "wait_for");
            }
            query.remove("waitForRefresh");
        }

        String requestId = UUID.randomUUID().toString();

        query.addProperty("requestId", requestId);

        if (!query.has("volatile")
            || query.get("volatile").isJsonNull()
        ) {
            query.add("volatile", new JsonObject());
        } else if (!query.get("volatile").isJsonObject()) {
            throw new InternalException("Volatile data must be a JsonObject", 400);
        }

        query.get("volatile")
             .getAsJsonObject()
             .addProperty("sdkVersion", version);

        query.get("volatile")
             .getAsJsonObject()
             .addProperty("sdkInstanceId", instanceId);

        if (networkProtocol.getState() == ProtocolState.OPEN) {
            networkProtocol.send(query);
        }

        Task<Response> task = new Task<>();
        requests.put(requestId, task);
        return task.getFuture();
    }
}
