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
    ConcurrentHashMap<String, Object> body1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> body2 = new ConcurrentHashMap<>();

    document1.put("_id", "some-id1");
    body1.put("key1", "value1");
    document1.put("body", body1);

    document2.put("_id", "some-id2");
    body2.put("key2", "value2");
    document2.put("body", body2);

    final ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleMock.getDocumentController().mCreate(index, collection, documents);
    Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

    assertEquals((arg.getValue()).getString("controller"), "document");
    assertEquals((arg.getValue()).getString("action"), "mCreate");
    assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
    assertEquals(((ArrayList<ConcurrentHashMap<String, Object>>)(((KuzzleMap)(arg.getValue()).get("body"))).get("documents")).get(0).get("_id").toString(), "some-id1");
    assertEquals(((ArrayList<ConcurrentHashMap<String, Object>>)(((KuzzleMap)(arg.getValue()).get("body"))).get("documents")).get(1).get("_id").toString(), "some-id2");
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
    ConcurrentHashMap<String, Object> body1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> body2 = new ConcurrentHashMap<>();

    document1.put("_id", "some-id1");
    body1.put("key1", "value1");
    document1.put("body", body1);

    document2.put("_id", "some-id2");
    body2.put("key2", "value2");
    document2.put("body", body2);

    final ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    kuzzleMock.getDocumentController().mCreate(index, collection, documents);
  }
}
