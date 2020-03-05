package io.kuzzle.sdk.Events;

public interface EventListener {
  void trigger(Object... args);
}
