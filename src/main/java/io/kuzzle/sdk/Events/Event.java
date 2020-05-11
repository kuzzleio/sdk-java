package io.kuzzle.sdk.Events;

/**
 * The enum Event type.
 */
public enum Event {
  disconnected,
  reconnected,
  connected,
  error,
  tokenExpired,
  loginAttempt,
  offlineQueuePush,
  offlineQueuePop,
  unhandledResponse,
  networkResponseReceived,
  networkStateChange
}
