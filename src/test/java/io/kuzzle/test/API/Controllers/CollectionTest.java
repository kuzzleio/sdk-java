package io.kuzzle.test.API.Controllers;

import com.google.gson.internal.LazilyParsedNumber;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
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

  @Test
  public void refreshCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().refresh(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "refresh");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void refreshCollectionShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().refresh(index, collection);
  }

  @Test
  public void deleteSpecificationsCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().deleteSpecifications(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "deleteSpecifications");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void deleteSpecificationsCollectionThrowWhenNotConnected() throws NotConnectedException, InternalException {

    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().deleteSpecifications(index, collection);
  }

  @Test
  public void getSpecificationsCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getCollectionController().getSpecifications(index, collection);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "getSpecifications");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
  }

  @Test(expected = NotConnectedException.class)
  public void getSpecificationsCollectionThrowWhenNotConnected() throws NotConnectedException, InternalException {

    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getCollectionController().getSpecifications(index, collection);
  }

  @Test
  public void validateSpecificationsCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> fields = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "string");
    fields.put("license", license);
    specifications.put("fields", fields);

    kuzzleMock.getCollectionController().validateSpecifications(index, collection, specifications);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "validateSpecifications");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
    assertEquals((
        (ConcurrentHashMap<String, Object>) (
            (ConcurrentHashMap<String, Object>) (
                ((ConcurrentHashMap<String, Object>)
                    ((arg.getValue())
                        .get("body")))
                    .get("fields")))
            .get("license"))
        .get("type").toString(), "string");
  }

  @Test(expected = NotConnectedException.class)
  public void validateSpecificationsCollectionThrowWhenNotConnected() throws NotConnectedException, InternalException {

    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();

    kuzzleMock.getCollectionController().validateSpecifications(index, collection, specifications);
  }

  @Test
  public void updateSpecificationsCollectionTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> fields = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "string");
    fields.put("license", license);
    specifications.put("fields", fields);

    kuzzleMock.getCollectionController().updateSpecifications(index, collection, specifications);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "collection");
    assertEquals((arg.getValue()).getString("action"), "updateSpecifications");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals((arg.getValue()).getString("collection"), "yellow-taxi");
    assertEquals((
        (ConcurrentHashMap<String, Object>) (
            (ConcurrentHashMap<String, Object>) (
                ((ConcurrentHashMap<String, Object>)
                    ((arg.getValue())
                        .get("body")))
                    .get("fields")))
            .get("license"))
        .get("type").toString(), "string");
  }

  @Test(expected = NotConnectedException.class)
  public void updateSpecificationsCollectionThrowWhenNotConnected() throws NotConnectedException, InternalException {

    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();

    kuzzleMock.getCollectionController().updateSpecifications(index, collection, specifications);
  }

//  @Test
//  public void searchSpecificationsTestA() throws NotConnectedException, InternalException, ExecutionException, InterruptedException {
//
//    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
//
//    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
//    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
//    ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
//    match.put("Hello", "Clarisse");
//    query.put("match", match);
//    searchQuery.put("query", query);
//
//    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);
//    CompletableFuture<Response> queryResponse = new CompletableFuture<>();
//
//    Response r = new Response();
//    r.result = new ConcurrentHashMap<String, Object>();
//    ((ConcurrentHashMap<String, Object>) r.result).put("aggregations", new ConcurrentHashMap<String, Object>());
//    ((ConcurrentHashMap<String, Object>) r.result).put("total", new LazilyParsedNumber("1"));
//    ((ConcurrentHashMap<String, Object>) r.result).put("scrollId", "");
//    ((ConcurrentHashMap<String, Object>) r.result).put("hits", new ArrayList<ConcurrentHashMap<String, Object>>());
//    doReturn(queryResponse).when(kuzzleMock).query(any(ConcurrentHashMap.class));
//
//    new Thread(() -> {
//      try {
//        kuzzleMock.getCollectionController().searchSpecifications(searchQuery);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }).start();
//    queryResponse.complete(r);
//    Thread.sleep(1000);
//    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());
//
//    assertEquals((arg.getValue()).getString("controller"), "collection");
//    assertEquals((arg.getValue()).getString("action"), "searchSpecifications");
//    assertEquals(((ConcurrentHashMap<String, Object>) ((ConcurrentHashMap<String, Object>) (((KuzzleMap) (arg.getValue()).get("body"))).get("query")).get("match")).get("Hello"), "Clarisse");
//
//  }
//
//  @Test
//  public void searchSpecificationsTestB() throws NotConnectedException, InternalException, ExecutionException, InterruptedException {
//
//    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
//    SearchOptions options = new SearchOptions();
//    options.setSize(10);
//    options.setFrom(0);
//    options.setScroll("30s");
//
//    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
//    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
//    ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
//    match.put("Hello", "Clarisse");
//    query.put("match", match);
//    searchQuery.put("query", query);
//
//    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);
//    CompletableFuture<Response> queryResponse = new CompletableFuture<>();
//
//    Response r = new Response();
//    r.result = new ConcurrentHashMap<String, Object>();
//    ((ConcurrentHashMap<String, Object>) r.result).put("aggregations", new ConcurrentHashMap<String, Object>());
//    ((ConcurrentHashMap<String, Object>) r.result).put("total", new LazilyParsedNumber("1"));
//    ((ConcurrentHashMap<String, Object>) r.result).put("scrollId", "");
//    ((ConcurrentHashMap<String, Object>) r.result).put("hits", new ArrayList<ConcurrentHashMap<String, Object>>());
//    doReturn(queryResponse).when(kuzzleMock).query(any(ConcurrentHashMap.class));
//
//    new Thread(() -> {
//      try {
//        kuzzleMock.getCollectionController().searchSpecifications(searchQuery, options);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }).start();
//    queryResponse.complete(r);
//    Thread.sleep(1000);
//    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());
//
//    assertEquals((arg.getValue()).getString("controller"), "collection");
//    assertEquals((arg.getValue()).getString("action"), "searchSpecifications");
//    assertEquals((arg.getValue()).getNumber("from"), 0);
//    assertEquals((arg.getValue()).getNumber("size"), 10);
//    assertEquals((arg.getValue()).getString("scroll"), "30s");
//
//    assertEquals(((ConcurrentHashMap<String, Object>) ((ConcurrentHashMap<String, Object>) (((KuzzleMap) (arg.getValue()).get("body"))).get("query")).get("match")).get("Hello"), "Clarisse");
//
//  }
//
//  @Test(expected = NotConnectedException.class)
//  public void searchSpecificationsShouldThrowWhenNotConnected() throws NotConnectedException, InternalException, ExecutionException, InterruptedException {
//    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
//    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);
//
//    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
//
//    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
//    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
//    match.put("Hello", "Clarisse");
//    searchQuery.put("match", match);
//
//    kuzzleMock.getCollectionController().searchSpecifications(searchQuery);
//  }
}
