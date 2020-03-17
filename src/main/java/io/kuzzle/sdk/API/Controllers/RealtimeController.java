package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Handlers.NotificationHandler;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SubscribeOptions;
import io.kuzzle.sdk.Protocol.ProtocolState;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RealtimeController extends BaseController {
  private class Subscription {
    public String index;
    public String collection;
    public ConcurrentHashMap<String, Object> filter;
    public NotificationHandler handler;
    public SubscribeOptions options;

    public Subscription(final String index,
                        final String collection,
                        final ConcurrentHashMap<String, Object> filter,
                        final NotificationHandler handler,
                        final SubscribeOptions options) {
      this.index = index;
      this.collection = collection;
      this.filter = filter;
      this.handler = handler;
      this.options = (options != null ? options : new SubscribeOptions());
    }
  }

  private ConcurrentHashMap<String, ArrayList<Subscription>> currentSubscriptions = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, ArrayList<Subscription>> subscriptionsCache = new ConcurrentHashMap<>();

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

      ArrayList<Subscription> subs = RealtimeController.this.currentSubscriptions.get(((Response) args[0]).room);

      if (subs != null) {
        final String instanceId = sdkInstanceId;
        subs.forEach(sub -> {
          if (sub != null && (instanceId.equals(kuzzle.instanceId) && sub.options.isSubscribeToSelf() || !instanceId.equals(kuzzle.instanceId))) {
            sub.handler.run(response);
          }
        });
      }
    });
    kuzzle.register(Event.networkStateChange, state -> {
      if (state[0] == ProtocolState.CLOSE) {
        this.currentSubscriptions.clear();
      }
    });
  }

  /**
   * Returns the number of other connections sharing the same subscription.
   *
   * @param roomId
   * @return
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Integer> count(final String roomId) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "realtime")
            .put("action", "count")
            .put("body", new KuzzleMap().put("roomId", roomId)))
        .thenApplyAsync((response) -> ((KuzzleMap) response.result).getNumber("count").intValue());
  }

  /**
   * Sends a real-time message to Kuzzle. The message will be dispatched to
   * all clients with subscriptions matching the index, the collection and
   * the message content.
   *
   * @param index
   * @param collection
   * @param message
   * @return
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> publish(final String index, final String collection, final ConcurrentHashMap<String, Object> message) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "realtime")
            .put("action", "publish")
            .put("index", index)
            .put("collection", collection)
            .put("body", new KuzzleMap().put("message", message)))
        .thenApplyAsync((response) -> null);
  }

  public void renewSubscriptions() {
    for (Map.Entry sub : subscriptionsCache.entrySet()) {
      subscriptionsCache.get(sub.getKey()).clear();
      ((ArrayList<Subscription>) sub.getValue()).forEach(subscription ->
      {
        try {
          subscribe(subscription);
        } catch (NotConnectedException e) {
          e.printStackTrace();
        } catch (InternalException e) {
          e.printStackTrace();
        }
      });
    }
  }
  
  /**
   * Subscribe to a collection.
   *
   * @param index
   * @param collection
   * @param filters
   * @param handler
   * @param options
   * @return
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<String> subscribe(final String index, final String collection, final ConcurrentHashMap<String, Object> filters, final NotificationHandler handler, final SubscribeOptions options) throws NotConnectedException, InternalException {
    ConcurrentHashMap<String, Object> queryOptions = new ConcurrentHashMap<>();
    final SubscribeOptions opts = (options == null ? new SubscribeOptions() : new SubscribeOptions(options));

    synchronized (RealtimeController.class) {
      if (opts != null) {
        queryOptions = opts.toHashMap();
      }
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
                  index,
                  collection,
                  filters,
                  handler,
                  opts
              );

              if (currentSubscriptions.get(channel) == null) {
                ArrayList<Subscription> item = new ArrayList<>();
                item.add(subscription);
                currentSubscriptions.put(channel, item);
                subscriptionsCache.put(channel, item);
              } else {
                currentSubscriptions.get(channel).add(subscription);
                subscriptionsCache.get(channel).add(subscription);
              }

              return ((ConcurrentHashMap<String, Object>) response.result).get("roomId").toString();
            });
  }

  private CompletableFuture<String> subscribe(final Subscription subscribe) throws NotConnectedException, InternalException {
    return subscribe(
        subscribe.index,
        subscribe.collection,
        subscribe.filter,
        subscribe.handler,
        subscribe.options
    );
  }

  public CompletableFuture<String> subscribe(final String index, final String collection, final ConcurrentHashMap<String, Object> filters, final NotificationHandler handler) throws NotConnectedException, InternalException {
    return this.subscribe(index, collection, filters, handler, null);
  }

  /**
   * Removes a subscription
   *
   * @param roomId
   * @return
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> unsubscribe(final String roomId) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "realtime")
            .put("action", "unsubscribe")
            .put("body", new KuzzleMap().put("roomId", roomId)))
        .thenApplyAsync((response) -> {
          ArrayList<Subscription> subs = currentSubscriptions.get(roomId);
          if (subs != null) {
            currentSubscriptions.get(roomId).clear();
          }
          subs = subscriptionsCache.get(roomId);
          if (subs != null) {
            subscriptionsCache.get(roomId).clear();
          }
          return null;
        });
  }
}
