package io.kuzzle.test.API.Controllers.DocumentTest;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.DocumentOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

 @Test
 public void createDocumentTestA() throws NotConnectedException, InternalException {

   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
   String index = "nyc-open-data";
   String collection = "yellow-taxi";

   ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
   document.put("name", "Yoann");
   document.put("nickname", "El angel de la muerte que hace el JAVA");

   ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

   DocumentOptions options = new DocumentOptions();
   options.setId("some-id");
   options.setWaitForRefresh(true);

   kuzzleMock.getDocumentController().create(index, collection, document, options);
   Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

   assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
   assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "create");
   assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
   assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
   assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), true);
   assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("name").toString(), "Yoann");
   assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("nickname").toString(), "El angel de la muerte que hace el JAVA");
 }

 @Test
 public void createDocumentTestB() throws NotConnectedException, InternalException {

   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
   String index = "nyc-open-data";
   String collection = "yellow-taxi";

   ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
   document.put("name", "Yoann");
   document.put("nickname", "El angel de la muerte que hace el JAVA");

   ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

   kuzzleMock.getDocumentController().create(index, collection, document);
   Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

   assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
   assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "create");
   assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
   assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), null);
   assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), null);
   assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("name").toString(), "Yoann");
   assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("nickname").toString(), "El angel de la muerte que hace el JAVA");
 }

 @Test(expected = NotConnectedException.class)
 public void createDocumentThrowWhenNotConnected() throws NotConnectedException, InternalException {
   AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
   Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

   Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
   String index = "nyc-open-data";
   String collection = "yellow-taxi";

   ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
   document.put("name", "Yoann");
   document.put("nickname", "El angel de la muerte que hace el JAVA");

   kuzzleMock.getDocumentController().create(index, collection, document);
 }

 @Test
 public void mGetDocumentTest() throws NotConnectedException, InternalException {

   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
   String index = "nyc-open-data";
   String collection = "yellow-taxi";

   final ArrayList<String> ids = new ArrayList<>();
   ids.add("some-id1");
   ids.add("some-id2");
   ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

   kuzzleMock.getDocumentController().mGet(index, collection, ids);
   Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

   assertEquals((arg.getValue()).getString("controller"), "document");
   assertEquals((arg.getValue()).getString("action"), "mGet");
   assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(0), "some-id1");
   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(1), "some-id2");
 }

 @Test(expected = NotConnectedException.class)
 public void mGetDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
   AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
   Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

   Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
   String index = "nyc-open-data";
   String collection = "yellow-taxi";

   final ArrayList<String> ids = new ArrayList<>();
   ids.add("some-id1");
   ids.add("some-id2");

   kuzzleMock.getDocumentController().mGet(index, collection, ids);
 }

  @Test
  public void replaceDocumentTestA() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "Yoann");
    document.put("nickname", "El angel de la muerte que hace el JAVA");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().replace(index, collection, "some-id", document, true);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "replace");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
    assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), true);
    assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("name").toString(), "Yoann");
    assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("nickname").toString(), "El angel de la muerte que hace el JAVA");
  }

  @Test
  public void replaceDocumentTestB() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "Yoann");
    document.put("nickname", "El angel de la muerte que hace el JAVA");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().replace(index, collection, "some-id", document);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "replace");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
    assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), null);
    assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("name").toString(), "Yoann");
    assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("nickname").toString(), "El angel de la muerte que hace el JAVA");
  }

  @Test(expected = NotConnectedException.class)
  public void replaceDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "Yoann");
    document.put("nickname", "El angel de la muerte que hace el JAVA");

    kuzzleMock.getDocumentController().replace(index, collection, "some-id", document);
  }

  @Test
  public void deleteDocumentTestA() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().delete(index, collection, "some-id", true);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "delete");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
    assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), true);
  }

  @Test
  public void deleteDocumentTestB() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().delete(index, collection, "some-id");
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "delete");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
    assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), null);
  }

  @Test(expected = NotConnectedException.class)
  public void deleteDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    kuzzleMock.getDocumentController().delete(index, collection, "some-id");
  }

  @Test
  public void mGetDocumentTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    final ArrayList<String> ids = new ArrayList<>();
    ids.add("some-id1");
    ids.add("some-id2");
    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().mGet(index, collection, ids);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "document");
    assertEquals((arg.getValue()).getString("action"), "mGet");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(0), "some-id1");
    assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(1), "some-id2");
  }

  @Test(expected = NotConnectedException.class)
  public void mGetDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    final ArrayList<String> ids = new ArrayList<>();
    ids.add("some-id1");
    ids.add("some-id2");

    kuzzleMock.getDocumentController().mGet(index, collection, ids);
  }
}
