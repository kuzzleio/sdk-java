package io.kuzzle.test.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void createTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getIndexController().create("my-index");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("create", ((KuzzleMap) arg.getValue()).getString("action"));
    assertEquals("my-index", ((KuzzleMap) arg.getValue()).getString("index"));
  }

  @Test
  public void deleteTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getIndexController().delete("my-index");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("delete", ((KuzzleMap) arg.getValue()).getString("action"));
    assertEquals("my-index", ((KuzzleMap) arg.getValue()).getString("index"));
  }

  @Test
  public void existsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getIndexController().exists("my-index");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("exists", ((KuzzleMap) arg.getValue()).getString("action"));
    assertEquals("my-index", ((KuzzleMap) arg.getValue()).getString("index"));
  }

  @Test
  public void listTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getIndexController().list();
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("list", ((KuzzleMap) arg.getValue()).getString("action"));
  }

  @Test
  public void mDeleteTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    ArrayList<String> indexes = new ArrayList<>();
    indexes.add("index1");
    indexes.add("index2");

    kuzzleSpy.getIndexController().mDelete(indexes);
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals("index", ((KuzzleMap) arg.getValue()).getString("controller"));
    assertEquals("mDelete", ((KuzzleMap) arg.getValue()).getString("action"));
    assertEquals("index1", ((ArrayList<String>)((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("indexes")).get(0));
    assertEquals("index2", ((ArrayList<String>)((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("indexes")).get(1));
  }
}
