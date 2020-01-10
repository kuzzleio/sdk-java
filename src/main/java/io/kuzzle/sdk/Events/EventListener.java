package io.kuzzle.sdk.Events;

public interface EventListener {
  void notify(Object... args);
}
