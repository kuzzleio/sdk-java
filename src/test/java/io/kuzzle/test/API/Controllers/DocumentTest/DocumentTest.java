package io.kuzzle.test.API.Controllers.DocumentTest;

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

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void mCreateDocumentTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();
    document1.put("name", "Yoann");
    document2.put("nickname", "El angel de la muerte que hace el JAVA");
    ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().mCreate(index, collection, documents);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "mCreate");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((ArrayList<ConcurrentHashMap<String, Object>>)(((KuzzleMap) arg.getValue()).get("body"))).get(0).get("name").toString(), "Yoann");
    assertEquals(((ArrayList<ConcurrentHashMap<String, Object>>)(((KuzzleMap) arg.getValue()).get("body"))).get(1).get("nickname").toString(), "El angel de la muerte que hace el JAVA");
  }

  @Test(expected = NotConnectedException.class)
  public void mCreateDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();
    document1.put("name", "Yoann");
    document2.put("nickname", "El angel de la muerte que hace el JAVA");

    ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    kuzzleMock.getDocumentController().mCreate(index, collection, documents);
  }
}
