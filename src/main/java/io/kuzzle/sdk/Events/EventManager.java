package io.kuzzle.sdk.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {
  /**
   * List of listener.
   */
  protected ConcurrentHashMap<Event, List<EventListener>> listeners = new ConcurrentHashMap<>();

  /**
   * Register a listener.
   *
   * @param event
   * @param listener
   * @return If successfully registered.
   */
  public synchronized boolean register(final Event event,
      final EventListener listener) {
    listeners.computeIfAbsent(event, l -> new ArrayList<>());

    List<EventListener> eventListener = listeners.get(event);
    if (eventListener != null) {
      return listeners.get(event).add(listener);
    }
    return false;
  }

  /**
   * Unregister a listener.
   *
   * @param event
   * @param listener
   * @return If successfully unregistered.
   */
  public synchronized boolean unregister(final Event event,
      final EventListener listener) {
    if (!listeners.containsKey(event)) {
      return false;
    }
    return listeners.get(event).remove(listener);
  }

  /**
   * Triggers every listener of the same event type.
   *
   * @param args
   *               An array of Object.
   */
  public void trigger(final Event event, Object... args) {
    List<EventListener> eventListener = listeners.get(event);
    if (eventListener != null) {
      for (final EventListener listener : eventListener) {
        if (listener != null) {
          listener.trigger(args);
        }
      }
    }
  }
}
