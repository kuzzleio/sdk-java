package io.kuzzle.sdk.Options;

import java.util.function.Predicate;

import static io.kuzzle.sdk.Helpers.Default.notNull;

public class KuzzleOptions<T> {
    
    private int maxQueueSize = -1;
    private int minTokenDuration = 3_600_000;
    private int refreshedTokenDuration = 3_600_000;
    private int maxRequestDelay = 1000;

    private boolean autoReconnect = true;

    private Predicate<T> filter = (T obj) -> true;

    public KuzzleOptions() {}

    public KuzzleOptions(KuzzleOptions options) {
        this.maxQueueSize = options.maxQueueSize;
        this.minTokenDuration = options.minTokenDuration;
        this.refreshedTokenDuration = options.refreshedTokenDuration;
        
        this.maxRequestDelay = options.maxRequestDelay;
        this.autoReconnect = options.autoReconnect;
        
        this.filter = options.filter;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public KuzzleOptions withMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize < 0
                ? -1
                : maxQueueSize;

        return this;
    }

    public int getMinTokenDuration() {
        return minTokenDuration;
    }

    public KuzzleOptions withMinTokenDuration(int minTokenDuration) {
        this.minTokenDuration = minTokenDuration < 0
                ? -1
                : minTokenDuration;

        return this;
    }

    public int getRefreshedTokenDuration() {
        return refreshedTokenDuration;
    }

    public KuzzleOptions withRefreshedTokenDuration(int refreshedTokenDuration) {
        this.refreshedTokenDuration = refreshedTokenDuration < 0
                ? -1
                : refreshedTokenDuration;

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

    public Predicate<T> getFilter() {
        return filter;
    }

    public KuzzleOptions withFilter(Predicate<T> filter) {
        this.filter = notNull(filter, (T obj) -> true);
        return this;
    }
}
