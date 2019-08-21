package io.kuzzle.sdk.Options;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.scripts.JO;
import org.json.JSONObject;

import java.util.function.Function;
import java.util.function.Predicate;

public class KuzzleOptions {
    
    private int maxQueueSize = -1;
    private int minTokenDuration = 3_600_000;
    private int refreshedTokenDuration = 3_600_000;
    private int maxRequestDelay = 1000;

    private boolean autoReconnect = true;
    private boolean autoReplay = false;
    private boolean autoResubscribe = true;

    private Predicate<JSONObject> filter = (JSONObject obj) -> true;

    public KuzzleOptions() {}

    public KuzzleOptions(KuzzleOptions options) {
        this.maxQueueSize = options.maxQueueSize;
        this.minTokenDuration = options.minTokenDuration;
        this.refreshedTokenDuration = options.refreshedTokenDuration;
        
        this.maxRequestDelay = options.maxRequestDelay;
        this.autoReconnect = options.autoReconnect;
        this.autoReplay = options.autoReplay;
        this.autoResubscribe = options.autoResubscribe;
        
        this.filter = options.filter;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public KuzzleOptions withMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize < 0 ? -1 : maxQueueSize;
        return this;
    }

    public int getMinTokenDuration() {
        return minTokenDuration;
    }

    public KuzzleOptions withMinTokenDuration(int minTokenDuration) {
        this.minTokenDuration = minTokenDuration < 0 ? -1 : minTokenDuration;
        return this;
    }

    public int getRefreshedTokenDuration() {
        return refreshedTokenDuration;
    }

    public KuzzleOptions withRefreshedTokenDuration(int refreshedTokenDuration) {
        this.refreshedTokenDuration = refreshedTokenDuration < 0 ? -1 : refreshedTokenDuration;
        return this;
    }

    public int getMaxRequestDelay() {
        return maxRequestDelay;
    }

    public KuzzleOptions withMaxRequestDelay(int maxRequestDelay) {
        this.maxRequestDelay = maxRequestDelay;
        return this;
    }

    public boolean isAutoReconnected() {
        return autoReconnect;
    }

    public KuzzleOptions withAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
        return this;
    }

    public boolean isAutoReplayed() {
        return autoReplay;
    }

    public KuzzleOptions withAutoReplay(boolean autoReplay) {
        this.autoReplay = autoReplay;
        return this;
    }

    public boolean isAutoResubscribed() {
        return autoResubscribe;
    }

    public KuzzleOptions withAutoResubscribe(boolean autoResubscribe) {
        this.autoResubscribe = autoResubscribe;
        return this;
    }

    public Predicate<JSONObject> getFilter() {
        return filter;
    }

    public KuzzleOptions withFilter(Predicate<JSONObject> filter) {
        this.filter = filter != null ? filter : ((JSONObject object) -> true);
        return this;
    }
}
