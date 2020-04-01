package io.kuzzle.test.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class CollectionTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void existsCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";


    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().exists(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "exists");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void existsCollectionShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().exists(index, collection);
  }

  @Test
  public void truncateCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().truncate(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "truncate");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void truncateCollectionShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().truncate(index, collection);
  }

  @Test
  public void createCollectionTestA() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().create(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "create");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
  }

  @Test
  public void createCollectionTestB() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    ConcurrentHashMap<String, Object> mapping = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    license.put("type", "keyword");
    properties.put("license", license);
    mapping.put("properties", properties);

    kuzzleMock.getCollectionController().create(index, collection, mapping);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "create");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
    assertEquals((
        (ConcurrentHashMap<String, Object>) (
            (ConcurrentHashMap<String, Object>) (
                ((ConcurrentHashMap<String, Object>)
                    ((arg.getValue())
                        .get("body")))
                    .get("properties")))
            .get("license"))
        .get("type").toString(), "keyword");
  }

  @Test(expected = NotConnectedException.class)
  public void createCollectionThrowWhenNotConnected() throws NotConnectedException, InternalException {

    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().create(index, collection);
  }

  @Test
  public void getMappingCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";


    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().getMapping(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "getMapping");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void getMappingShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().getMapping(index, collection);
  }
}
