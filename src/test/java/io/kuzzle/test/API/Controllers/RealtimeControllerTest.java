package io.kuzzle.test.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Handlers.NotificationHandler;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SubscribeOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RealtimeControllerTest {
  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void subscribeTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    NotificationHandler notificationHandler = mock(NotificationHandler.class);

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getRealtimeController().subscribe("index", "collection", new ConcurrentHashMap<>(), notificationHandler, new SubscribeOptions());
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals("realtime", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("subscribe", ((KuzzleMap) arg.getValue()).getString("action"));
    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("index"));
    assertEquals("collection", ((KuzzleMap) arg.getValue()).getString("collection"));
  }

  @Test
  public void notificationHandlerTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    NotificationHandler notificationHandler = mock(NotificationHandler.class);
    Response response = new Response();
    response.room = "a-room";
    response.result = new ConcurrentHashMap<>();
    ((ConcurrentHashMap)response.result).put("roomId", "a-room");
    ((ConcurrentHashMap)response.result).put("channel", "a-room");

    CompletableFuture queryResponse = new CompletableFuture();

    doReturn(queryResponse).when(kuzzleSpy).query(any(ConcurrentHashMap.class));

    kuzzleSpy.getRealtimeController().subscribe("index", "collection", new ConcurrentHashMap<>(), notificationHandler, new SubscribeOptions());
    queryResponse.complete(response);
    Thread.sleep(1000);
    kuzzleSpy.trigger(Event.unhandledResponse, response);
    verify(notificationHandler).run(any(Response.class));
  }

  @Test
  public void notificationHandlerWithoutSubscribeToSelfTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    NotificationHandler notificationHandler = mock(NotificationHandler.class);
    Response response = new Response();
    response.room = "a-room";
    response.result = new ConcurrentHashMap<>();
    ((ConcurrentHashMap)response.result).put("roomId", "a-room");
    ((ConcurrentHashMap)response.result).put("channel", "a-room");
    response.Volatile = new ConcurrentHashMap<>();
    response.Volatile.put("sdkInstanceId", kuzzleSpy.instanceId);

    CompletableFuture queryResponse = new CompletableFuture();

    doReturn(queryResponse).when(kuzzleSpy).query(any(ConcurrentHashMap.class));

    SubscribeOptions options = new SubscribeOptions();
    options.setSubscribeToSelf(false);
    kuzzleSpy.getRealtimeController().subscribe("index", "collection", new ConcurrentHashMap<>(), notificationHandler, options);
    queryResponse.complete(response);
    kuzzleSpy.trigger(Event.unhandledResponse, response);
    verify(notificationHandler, never()).run(any(Response.class));
  }
}
