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

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void udpateDocumentTest() throws NotConnectedException, InternalException {

    Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";

    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();

    document.put("name", "Yoann");
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().update(index, collection, "some-id", document);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "update");
    assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
    assertEquals(((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("name").toString(), "Yoann");
  }

  @Test(expected = NotConnectedException.class)
  public void updateShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
    AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
    Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

    Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
    String index = "nyc-open-data";
    String collection = "yellow-taxi";
    String id = "some-id";

    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "Yoann");

    kuzzleMock.getDocumentController().update(index, collection, id, document);
  }
}
