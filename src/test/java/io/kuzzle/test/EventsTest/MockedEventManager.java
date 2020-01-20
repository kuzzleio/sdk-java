
package io.kuzzle.test.EventsTest;

import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Events.EventManager;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MockedEventManager extends EventManager {
  public MockedEventManager() {
    super();
  }

  public ConcurrentHashMap<Event, List<EventListener>> getListeners() {
    return super.listeners;
  }
}