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
  public boolean register(final Event event, final EventListener listener) {
    if (!listeners.containsKey(event)) {
      listeners.put(event, new ArrayList<EventListener>());
    }
    return listeners.get(event).add(listener);
  }

  /**
   * Unregister a listener.
   * 
   * @param event
   * @param callback
   * @return If successfully unregistered.
   */
  public boolean unregister(final Event event, final EventListener listener) {
    if (!listeners.containsKey(event)) {
      return false;
    }
    return listeners.get(event).remove(listener);
  }

  /**
   * Triggers every listener of the same event type.
   * 
   * @param obj An Object.
   */
  public void trigger(final Event event, Object... args) {
    for (final EventListener listener : listeners.get(event)) {
      listener.notify(args);
    }
  }
}
