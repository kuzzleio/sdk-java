package io.kuzzle.sdk.Events;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class EventListener <T> {

    protected Set<Object> callbacks;

    public EventListener() {
        callbacks = ConcurrentHashMap.newKeySet();
    }

    public boolean register(final Consumer<T> callback) {
        return callbacks.add(callback);
    }

    public boolean register(final Runnable callback) {
        return callbacks.add(callback);
    }

    public boolean unregister(final Consumer<T> callback) {
        return callbacks.remove(callback);
    }

    public boolean unregister(final Runnable callback) {
        return callbacks.remove(callback);
    }

    public void trigger(final T obj) {
        for (Object callback : callbacks) {
            if (callback instanceof Consumer) {
                ((Consumer<T>)callback).accept(obj);
            }
        }
    }

    public void trigger() {
        for (Object callback : callbacks) {
            if (callback instanceof Runnable) {
                ((Runnable)callback).run();
            }
        }
    }
}
