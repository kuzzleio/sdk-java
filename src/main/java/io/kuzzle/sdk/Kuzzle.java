package io.kuzzle.sdk;

import com.sun.istack.internal.NotNull;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Response.Task;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Kuzzle {

    private final AbstractProtocol networkProtocol;

    public final String version;
    public final String instanceId;

    protected int maxQueueSize;
    protected int minTokenDuration;
    protected int refreshedTokenDuration;
    protected int maxRequestDelay;

    protected boolean autoReconnect;
    protected boolean autoReplay;
    protected boolean autoResubscribe;

    protected ConcurrentHashMap<UUID, Task<JSONObject>> requests = new ConcurrentHashMap<>();

    public Kuzzle(@NotNull AbstractProtocol networkProtocol) throws URISyntaxException, IllegalArgumentException {
        this(networkProtocol, new KuzzleOptions());
    }

    public Kuzzle(@NotNull AbstractProtocol networkProtocol, final KuzzleOptions options) throws URISyntaxException, IllegalArgumentException {

        if (networkProtocol == null) {
            throw new IllegalArgumentException("NewtorkProtocol can't be null");
        }

        KuzzleOptions kOptions = options != null ? options : new KuzzleOptions();

        this.networkProtocol = networkProtocol;
        this.networkProtocol.registerMessageEvent(this::ResponseListener);

        this.maxQueueSize = kOptions.getMaxQueueSize();
        this.minTokenDuration = kOptions.getMinTokenDuration();
        this.refreshedTokenDuration = kOptions.getRefreshedTokenDuration();
        this.maxRequestDelay = kOptions.getMaxRequestDelay();

        this.autoReconnect = kOptions.isAutoReconnected();
        this.autoReplay = kOptions.isAutoReplayed();
        this.autoResubscribe = kOptions.isAutoResubscribed();

        this.version = "2.0";
        this.instanceId = UUID.randomUUID().toString();

    }

    public void connect() throws Exception {
        networkProtocol.connect();
    }

    public void disconnect() {
        networkProtocol.disconnect();
    }

    private void ResponseListener(String payload) {
        System.out.println(payload);
        System.out.println(this.instanceId);
    }

    public CompletableFuture<JSONObject> query(@NotNull JSONObject query) throws InternalException, NotConnectedException {
        if (query == null) {
            throw new InternalException("You must provide a query", 400);
        }

        if (networkProtocol.getState() == ProtocolState.CLOSE) {
            throw new NotConnectedException();
        }

        if (query.has("waitForRefresh")) {
            if (query.optBoolean("waitForRefresh", false)) {
                query.put("refresh", "wait_for");
            }
            query.remove("waitForRefresh");
        }

        UUID requestId = UUID.randomUUID();

        query.put("requestId", requestId.toString());

        if (query.isNull("volatile")) {
            query.put("volatile", new JSONObject());
        } else if (!(query.get("volatile") instanceof JSONObject)) {
            throw new InternalException("Volatile data must be a JSONObject", 400);
        }

        query.getJSONObject("volatile").put("sdkVersion", version);
        query.getJSONObject("volatile").put("sdkInstanceId", instanceId);

        if (networkProtocol.getState() == ProtocolState.OPEN) {
            networkProtocol.send(query);
        }

        Task<JSONObject> task = new Task<>();
        requests.put(requestId, task);
        return task.getFuture();
    }



}
