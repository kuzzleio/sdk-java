package io.kuzzle.sdk.Events;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class EventListener<T> {

    /**
     * Set of registered callbacks.
     */
    protected Set<Object> callbacks;

    /**
     * Initializes a new instance of the EventListener.
     */
    public EventListener() {
        callbacks = ConcurrentHashMap.newKeySet();
    }

    /** Register a callback that takes a parameter.
     * @param callback
     * @return If successfully registered.
     */
    public boolean register(final Consumer<T> callback) {
        return callbacks.add(callback);
    }

    /** Register a callback with no parameter.
     * @param callback
     * @return If successfully registered.
     */
    public boolean register(final Runnable callback) {
        return callbacks.add(callback);
    }

    /** Unregister a callback that takes a parameter.
     * @param callback
     * @return If successfully unregistered.
     */
    public boolean unregister(final Consumer<T> callback) {
        return callbacks.remove(callback);
    }

    /** Unregister a callback with no parameter.
     * @param callback
     * @return If successfully unregistered.
     */
    public boolean unregister(final Runnable callback) {
        return callbacks.remove(callback);
    }

    /** Triggers every callbacks that have a parameter with the given object.
     * @param obj An Object.
     */
    public void trigger(final T obj) {
        for (Object callback : callbacks) {
            if (callback instanceof Consumer) {
                ((Consumer<T>)callback).accept(obj);
            }
        }
    }

    /**
     * Triggers every callbacks that doesn't have a parameter.
     */
    public void trigger() {
        for (Object callback : callbacks) {
            if (callback instanceof Runnable) {
                ((Runnable)callback).run();
            }
        }
    }
}
