package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Handlers.NotificationHandler;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SubscribeOptions;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RealtimeController extends BaseController {
  private class Subscription {
    public boolean subscribeToSelf;
    public NotificationHandler handler;

    public Subscription(final boolean subscribeToSelf, final NotificationHandler handler) {
      this.subscribeToSelf = subscribeToSelf;
      this.handler = handler;
    }
  }

  private ConcurrentHashMap<String, ArrayList<Subscription>> subscriptions = new ConcurrentHashMap<>();

  public RealtimeController(Kuzzle kuzzle) {
    super(kuzzle);
    kuzzle.register(Event.unhandledResponse, args -> {
      Response response = (Response) args[0];

      if (response.error != null && response.error.id.equals("security.token.expired")) {
        kuzzle.trigger(Event.tokenExpired);
        return;
      }

      String sdkInstanceId = "";
      if (response.Volatile != null) {
        sdkInstanceId = response.Volatile.get("sdkInstanceId").toString();
      }

      ArrayList<Subscription> subs = RealtimeController.this.subscriptions.get(((Response) args[0]).room);

      if (subs != null) {
        final String instanceId = sdkInstanceId;
        subs.forEach(sub -> {
          if (sub != null && (instanceId.equals(kuzzle.instanceId) && sub.subscribeToSelf || !instanceId.equals(kuzzle.instanceId))) {
            sub.handler.run(response);
          }
        });
      }
    });
  }

  public CompletableFuture<String> subscribe(final String index, final String collection, final ConcurrentHashMap<String, Object> filters, final NotificationHandler handler, final SubscribeOptions options) throws NotConnectedException, InternalException {
    ConcurrentHashMap<String, Object> queryOptions = new ConcurrentHashMap<>();
    if (options != null) {
      queryOptions = options.toHashMap();
    }

    KuzzleMap query = new KuzzleMap(queryOptions)
        .put("controller", "realtime")
        .put("action", "subscribe")
        .put("index", index)
        .put("collection", collection)
        .put("body", filters);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> {
              String channel = ((ConcurrentHashMap<String, Object>) response.result).get("channel").toString();
              Subscription subscription = new Subscription(
                  options == null ? new SubscribeOptions().isSubscribeToSelf() : options.isSubscribeToSelf(),
                  handler
              );

              if (subscriptions.get(channel) == null) {
                ArrayList<Subscription> item = new ArrayList<>();
                item.add(subscription);
                subscriptions.put(channel, item);
              } else {
                subscriptions.get(channel).add(subscription);
              }

              return ((ConcurrentHashMap<String, Object>) response.result).get("roomId").toString();
            });
  }

  public CompletableFuture<String> subscribe(final String index, final String collection, final ConcurrentHashMap<String, Object> filters, final NotificationHandler handler) throws NotConnectedException, InternalException {
    return this.subscribe(index, collection, filters, handler, null);
  }
}
