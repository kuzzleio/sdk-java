package io.kuzzle.sdk.Events;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class EventListener <T> {

    private Set<Consumer<T>> events;

    public EventListener() {
        events = ConcurrentHashMap.newKeySet();
    }

    public boolean register(final Consumer<T> callback) {
        return events.remove(callback);
    }

    public boolean unregister(final Consumer<T> callback) {
        return events.remove(callback);
    }

    public void trigger(final T obj) {
        Iterator<Consumer<T>> iterator = events.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(obj);
        }
    }
}
