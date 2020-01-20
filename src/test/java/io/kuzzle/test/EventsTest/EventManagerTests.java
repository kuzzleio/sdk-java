package io.kuzzle.test.EventsTest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;

import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Events.EventListener;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventManagerTests {

  @Test
  public void registerRunnableTest() {
    MockedEventManager eventManager = new MockedEventManager();

    eventManager.register(Event.connected, (Object... args) -> {
    });

    Assert.assertEquals(1, eventManager.getListeners().size());
  }

  @Test
  public void unregisterRunnableTest() {
    MockedEventManager eventManager = new MockedEventManager();
    EventListener listener = (Object... args) -> {
    };

    eventManager.register(Event.connected, listener);
    eventManager.unregister(Event.connected, listener);

    Assert.assertEquals(0, eventManager.getListeners().get(Event.connected).size());
  }

  @Test
  public void registerDuplicateTest() {
    MockedEventManager eventManager = new MockedEventManager();

    EventListener listener = (Object... args) -> {
    };
    eventManager.register(Event.connected, listener);
    eventManager.register(Event.connected, listener);
    eventManager.register(Event.connected, listener);
    eventManager.register(Event.connected, listener);
    eventManager.register(Event.connected, listener);
    eventManager.register(Event.connected, (Object... args) -> {
    });

    Assert.assertEquals(6, eventManager.getListeners().get(Event.connected).size());
  }

  @Test
  public void triggerRunnableTest() {
    MockedEventManager eventManager = new MockedEventManager();

    AtomicBoolean success = new AtomicBoolean(false);
    eventManager.register(Event.connected, (Object... args) -> success.set(true));

    Assert.assertFalse(success.get());

    eventManager.trigger(Event.connected);

    Assert.assertTrue(success.get());
  }

  @Test
  public void triggerListenerTest() {
    MockedEventManager eventManager = new MockedEventManager();

    AtomicBoolean success = new AtomicBoolean(false);
    eventManager.register(Event.connected, (Object... args) -> success.set(args[0].toString().equals("foobar")));

    Assert.assertFalse(success.get());

    eventManager.trigger(Event.connected, "foobar");

    Assert.assertTrue(success.get());
  }
}
