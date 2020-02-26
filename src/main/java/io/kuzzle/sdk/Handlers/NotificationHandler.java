package io.kuzzle.sdk.Handlers;

import io.kuzzle.sdk.CoreClasses.Responses.Response;

public interface NotificationHandler {
  void run(Response notification);
}
